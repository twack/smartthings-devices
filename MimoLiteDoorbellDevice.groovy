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
 *	Instructions: 	Once you install this device type into smartthings and have the mimolite hooked up to your
 *					doorbell you will need to configure the mimolite. Go to the doorbell and hold the doorbell
 *					button down. At the same time, you will need to tap the "configure" button on this device's
 *					detail screen. After 5 seconds, you can release the doorbell button.
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
        capability "Polling"
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
        standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat") {
			state "default", action:"refresh.refresh", icon:"st.secondary.refresh"
		} 
		main (["button"])
		details(["button", "configure", "refresh"])
	}
}

def pushed() {

	//Uncomment the two lines below to simulate the doorbell being pushed. This is handy for connected app testing.
    
	//sendEvent( name : "button", value: "pushed", descriptionText: "$device.displayName was pressed", unit : "" )
	//doorbellTimerEventHandler()
}


def parse(String description) {
	log.debug "description is: ${description}"

	def result = null
	def cmd = zwave.parse(description, [0x20: 1, 0x84: 1, 0x30: 1, 0x70: 1])
    
    if (cmd.CMD == "7105") {				//Mimo sent a power report lost power (doorbell released)
        //doorbellTimerEventHandler()       // doesn't always fire for some users
    } else {
    	sendEvent( name : "button", value : "pushed", descriptionText: "$device.displayName was pressed", unit : "" )
        doorbellTimerEventHandler()
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
	sendEvent( name : "button", value: "default", descriptionText: "$device.displayName was released")
    refresh()
	log.debug "button is released"
}

def poll() {
	log.debug "state of button is: ${device.currentValue("button")}"
    sendEvent( name : "button", value: "${device.currentValue("button")}")
}

def refresh() {
	poll()
}

def configure() {
	log.debug "Configuring...." //setting up to monitor power alarm and actuator duration
	delayBetween([
		zwave.associationV1.associationSet(groupingIdentifier:3, nodeId:[zwaveHubNodeId]).format(),
        zwave.configurationV1.configurationSet(configurationValue: [25], parameterNumber: 11, size: 1).format(),
        zwave.configurationV1.configurationGet(parameterNumber: 11).format()
	])
}
