/**
 *  MimoLite Doorbell Device
 *
 *  Copyright 2014 Todd Wackford
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *	Instructions: 	                             ****** IMPORTANT ******
 * 					1. Pair the mimolite to SmartThings before installing the physical device into the chime casing.
 * 					2. Edit it's device type file to use this file. (I am assuming you know how.)
 * 					3. With device powered up and paired to SmartThings, go into the device detail screen and tap
 * 					   "configure". This sets the device to send a "power loss" signal.
 * 					4. Now you can pysically install the device into the chime.
 * 
 * 					If you have already paired the mimolite to smartthings and installed it into the chime casing,
 * 					but you have not run configure do the following:
 * 					1. Open the device detail screen on your mobile device.
 * 					2. Go to the doorbell button location (usually at front door).
 * 					3. Press and hold down the button while at the same time tap the "configure" tile in SmartThings.
 * 					4. Wait 5 seconds and release the doorbell button.
 *
 *					Now press and release your doorbell button as you normally would. You should see the device
 *					icon display a darker blue and the text "DING-DONG". This will display this way for 20 seconds.
 *
 *					This device is best used with an app that can subscribe to the "button.pushed" event from
 *					the device.
 */
metadata {
	// Automatically generated. Make future change here.
	definition (name: "MimoLite Doorbell Device", namespace: "wackford", author: "Todd Wackford") {
		capability "Configuration"
		capability "Button"
        capability "Refresh"

        command "pushed"
	}

	simulator {
	// Simulator stuff
    
	}

	// UI tile definitions 
	tiles {
		standardTile("button", "device.button", width: 2, height: 2) {
			state "default", label: ".....", action: "pushed", icon: "st.Home.home30", backgroundColor: "#B0E0E6"
            state "pushed", label: "Ding-Dong", icon: "st.Home.home30", backgroundColor: "#53a7c0"
		}
		standardTile("configure", "device.configure", inactiveLabel: false, decoration: "flat") {
			state "configure", label:'', action:"configuration.configure", icon:"st.secondary.configure"
		}
		main (["button"])
		details(["button", "configure"])
	}
}

def pushed() {
    //This method allows you to tap the main tile/icon to simulate someone pressing the doorbell.
	//Uncomment the two lines below to simulate the doorbell being pushed. Handy for testing a connected app.

	//sendEvent( name : "button", value: "pushed", descriptionText: "$device.displayName was pressed", unit : "" )
	//doorbellTimerEventHandler()
}

def parse(String description) {
	log.debug "description is: ${description}"

	def result = null
	def cmd = zwave.parse(description, [0x20: 1, 0x84: 1, 0x30: 1, 0x70: 1])
    
    if (cmd.CMD == "7105") {				//Mimo sent a power report lost power (doorbell released)
        doorbellTimerEventHandler()      
    } else {   
    	sendEvent( name : "button", value : "pushed", descriptionText: "$device.displayName was pressed", unit : "" )
    }
    
	if (cmd) {
		result = createEvent(zwaveEvent(cmd))
	}
    
	log.debug "Parse returned ${result?.descriptionText}"
    
	return result
}

def doorbellTimerEventHandler() {
	log.debug "in doorbellTimerEventHandler"
	runIn(15, "showButtonReleased")
}

def showButtonReleased() {
	sendEvent( name : "button", value: "false", descriptionText: "$device.displayName was released")
	log.debug "button is released"
}

def configure() {
	log.debug "Configuring...." //setting up to monitor power alarm and actuator duration
	delayBetween([
		zwave.associationV1.associationSet(groupingIdentifier:3, nodeId:[zwaveHubNodeId]).format(),
        zwave.configurationV1.configurationSet(configurationValue: [25], parameterNumber: 11, size: 1).format(),
        zwave.configurationV1.configurationGet(parameterNumber: 11).format()
	])
}
