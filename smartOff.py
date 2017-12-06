# Normalizing features
from numpy import transpose
from datetime import datetime
from keras.models import load_model
from pandas import DataFrame

def normalizeTable(data, maxUsage = -1):
    data[0] = data[0] / 12  # Change to 12
    data[1] = data[1] / 31
    data[2] = data[2] / 24
    data[3] = data[3] / 60
    data[4] = data[4] / 60
    if maxUsage != -1:
        data[5] = data[5] / maxUsage
    return data

def normalizeData(values, maxUsage=-1):
    transposed = transpose(values)
    scaledT = normalizeTable(transposed, maxUsage)
    scaled = transpose(scaledT)
    return scaled


def predictOnNext3Hours(model, s, maxUsage):
    timeS = datetime.fromtimestamp(float(s))
    print(timeS)
    timeList = []

    for i in range(0,180):
        tList = [7, timeS.day, timeS.hour+ int(i/60), timeS.minute + i % 60, timeS.second]
        timeList.append(tList)
    newData1 = DataFrame(timeList)
    newData = normalizeData(newData1.astype(float).values)
    pData = newData.reshape((newData.shape[0], 1, newData.shape[1]))
    prediction = model.predict(pData)
    usageValues = prediction * maxUsage
    # print(prediction[:10])
    #print(usageValues)
    return usageValues

def predictForSingleTime(model, s, maxUsage, applianceUsed):
    timeS = datetime.fromtimestamp(float(s))
    print(timeS)
    timeList = []
    tList = [7, timeS.day, timeS.hour, timeS.minute, timeS.second]
    timeList.append(tList)
    newData1 = DataFrame(timeList)
    newData = normalizeData(newData1.astype(float).values)
    pData = newData.reshape((newData.shape[0], 1, newData.shape[1]))
    prediction = model.predict(pData)
    usageValues = prediction * maxUsage
    print('Usage value', usageValues[0][0])
    return isOnOrOff(usageValues[0][0], applianceUsed)

# UNCOMMENT APPROPRIATE DEVICE BELOW
# NOTE THAT THIS SHOULD BE REPLACED BY THRESHOLD
# BEING COMPUTED FROM TRAINING DATA
#applianceUsed = 'MICROWAVE'
def getThresholdValue(applianceUsed):
    if (applianceUsed == 'TV'):
        return 25
    elif (applianceUsed == 'MICROWAVE'):
        return 8
    # UNKNOWN DEVICE
    return 1

def isOnOrOff(usageValue, applianceUsed):
    threshold = getThresholdValue(applianceUsed)
    if (usageValue > threshold):
        return True
    return False

def loadMicrowaveModel():
    return load_model('savedModels/full_Microwave_2.h5')

def loadTVModel():
    return load_model('savedModels/full_TV.h5')
