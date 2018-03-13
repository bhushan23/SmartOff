# SmartOff
Machine Learning and Internet of Things approach for turning off appliances when not used for saving power consumption.

### Objective
The idea is to build a system by combining IoT and Machine Learning methods for accurate prediction of a particular appliance usage from residential data, and cut off the power supply to that appliance when not is use. This will help reduce energy wastage from all devices that consume some power in standby mode for most of the day when not in use. 

### Approach
* Project is split into hardware (IoT) and software (Machine Learning) part as follows:
Internet of Things: The end goal is to create a system which doesn’t need much human intervention for successful functioning. We propose following two approaches:

* Locally attach a smart device to the appliance which will be connected to a central grid.
Centralize everything and manage the devices from one smart grid.
The aim in both the approaches is the same, i.e. cut off power supply to the appliance when not in use.
Machine Learning: In order to make the devices smart, we use machine learning to analyze the behavior of the consumer. To analyze behavior, here, means to recognize the patterns of the user’s usage of a particular appliance, such as, the time ranges when the devices are used the most or  the time ranges when the devices are in standby mode for most of the times and so on. Thus the system will know when the device is most used and when it is not. So, we can cut the power supply. We plan to make the models easily adaptable for new usage patterns. Hence, we’ll be incorporating Transfer Learning.


### Contributors
* Bhushan Sonawane
* Nishant Borude
* Ishupreet Singh
