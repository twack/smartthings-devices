/**
 *  Copyright 2015 SmartThings
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
 *  Z-Wave RGBW Light
 *
 *  Author: SmartThings
 *  Date: 2015-7-12
 */
 
 //Todo remove isTileCommand stuff
 //     figure better way of config

metadata {
	definition (name: "Fibaro RGBW 3 Beta", namespace: "twack", author: "Todd Wackford") {
		capability "Switch Level"
		capability "Actuator"
        capability "Color Control"
		capability "Color Temperature"
		capability "Switch"
		capability "Refresh"
		capability "Sensor"
        capability "Power Meter"

        command "redOn"
        command "redOff"
        command "greenOn"
        command "greenOff"
        command "blueOn"
        command "blueOff"
        command "whiteOn"
        command "whiteOff"
        
 		command "setRedLevel"
        command "setGreenLevel"
        command "setBlueLevel"
        command "setWhiteLevel"

        command "fireplaceOn"
        command "fireplaceOff"
        command "stormOn"
        command "stormOff"
        command "deepfadeOn"
        command "deepfadeOff"
        command "litefadeOn"
        command "litefadeOff"
        command "policeOn"
        command "policeOff"
        
        command "red"
        command "green"
        command "blue"
        command "white"
        command "cyan"
        command "magenta"
        command "orange"
        command "purple"
        command "yellow"
        command "pink"
        command "coldWhite"
        command "warmWhite"
        command "fireplace"
        command "storm"
        command "deepfade"
        command "litefade"
        command "police"
        
        command "reset"

		fingerprint deviceId: "0x1101", inClusters: "0x27,0x72,0x86,0x26,0x60,0x70,0x32,0x31,0x85,0x33"
	}

	simulator {
	}

	tiles (scale: 2){      
		multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label:'${name}', action:"switch.off", icon:"st.lights.philips.hue-single", backgroundColor:"#79b821", nextState:"turningOff"
				attributeState "off", label:'${name}', action:"switch.on", icon:"st.lights.philips.hue-single", backgroundColor:"#ffffff", nextState:"turningOn"
				attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.lights.philips.hue-single", backgroundColor:"#79b821", nextState:"turningOff"
				attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.lights.philips.hue-single", backgroundColor:"#ffffff", nextState:"turningOn"
			}
			tileAttribute ("device.level", key: "SLIDER_CONTROL") {
				attributeState "level", action:"switch level.setLevel"
			}
			tileAttribute ("device.color", key: "COLOR_CONTROL") {
				attributeState "color", action:"setColor"
			}
			tileAttribute ("power", key: "SECONDARY_CONTROL") {
				attributeState "power", label:'${currentValue} W'
			}
        }

        controlTile("colorTempControl", "device.colorTemperature", "slider", height: 1, width: 5, inactiveLabel: false) {
			state "colorTemperature", action:"setColorTemperature"
		}
        
////////////////////////// 
        standardTile("red", "device.red", height: 1, width: 1, inactiveLabel: false, canChangeIcon: false) {
            state "off", label:"R", action:"redOn", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "on", label:"R", action:"redOff", icon:"st.illuminance.illuminance.bright", backgroundColor:"#FF0000"
        }
        controlTile("redSliderControl", "device.redLevel", "slider", height: 1, width: 4, inactiveLabel: false) {
			state "redLevel", action:"setRedLevel"
		}
        valueTile("redValueTile", "device.redLevel", decoration: "flat", height: 1, width: 1) {
        	state "redLevel", label:'${currentValue}%'
        }     
        
        standardTile("green", "device.green", height: 1, width: 1, inactiveLabel: false, canChangeIcon: false) {
            state "off", label:"G", action:"greenOn", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "on", label:"G", action:"greenOff", icon:"st.illuminance.illuminance.bright", backgroundColor:"#00FF00"
        }
        controlTile("greenSliderControl", "device.greenLevel", "slider", height: 1, width: 4, inactiveLabel: false) {
			state "greenLevel", action:"setGreenLevel"
		}
        valueTile("greenValueTile", "device.greenLevel", decoration: "flat", height: 1, width: 1) {
        	state "greenLevel", label:'${currentValue}%'
        }    
        
        standardTile("blue", "device.blue", height: 1, width:1, inactiveLabel: false, canChangeIcon: false) {
            state "off", label:"B", action:"blueOn", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "on", label:"B", action:"blueOff", icon:"st.illuminance.illuminance.bright", backgroundColor:"#0000FF"
        }
        controlTile("blueSliderControl", "device.blueLevel", "slider", height: 1, width: 4, inactiveLabel: false) {
			state "blueLevel", action:"setBlueLevel"
		}
        valueTile("blueValueTile", "device.blueLevel", decoration: "flat", height: 1, width: 1) {
        	state "blueLevel", label:'${currentValue}%'
        }  
        
        standardTile("white", "device.white", height: 1, width: 1, inactiveLabel: false, canChangeIcon: false) {
            state "off", label:"W", action:"whiteOn", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "on", label:"W", action:"whiteOff", icon:"st.illuminance.illuminance.bright", backgroundColor:"#FFFFFF"
        }
        controlTile("whiteSliderControl", "device.whiteLevel", "slider", height: 1, width: 4, inactiveLabel: false) {
			state "whiteLevel", action:"setWhiteLevel"
		}
        valueTile("whiteValueTile", "device.whiteLevel", decoration: "flat", height: 1, width: 1) {
        	state "whiteLevel", label:'${currentValue}%'
        }  
        
        standardTile("fireplace", "device.fireplace", height: 2, width: 2, inactiveLabel: false, canChangeIcon: false) {
            state "off", label:"Fire Place", action:"fireplaceOn", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "on", label:"Fire Place", action:"fireplaceOff", icon:"st.illuminance.illuminance.bright", backgroundColor:"#FFFFFF"
        }
        standardTile("storm", "device.storm", height: 2, width: 2, inactiveLabel: false, canChangeIcon: false) {
            state "off", label:"storm", action:"stormOn", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "on", label:"storm", action:"stormOff", icon:"st.illuminance.illuminance.bright", backgroundColor:"#FFFFFF"
        }
        standardTile("deepfade", "device.deepfade", height: 2, width: 2, inactiveLabel: false, canChangeIcon: false) {
            state "off", label:"deep fade", action:"deepfadeOn", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "on", label:"deep fade", action:"deepfadeOff", icon:"st.illuminance.illuminance.bright", backgroundColor:"#FFFFFF"
        }
        standardTile("litefade", "device.litefade", height: 2, width: 2, inactiveLabel: false, canChangeIcon: false) {
            state "off", label:"lite fade", action:"litefadeOn", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "on", label:"lite fade", action:"litefadeOff", icon:"st.illuminance.illuminance.bright", backgroundColor:"#FFFFFF"
        }
        standardTile("police", "device.police", height: 2, width: 2, inactiveLabel: false, canChangeIcon: false) {
            state "off", label:"police", action:"policeOn", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "on", label:"police", action:"policeOff", icon:"st.illuminance.illuminance.bright", backgroundColor:"#FFFFFF"
        }
        
        
///////////////////        
        standardTile("cyan", "device.cyan", height: 2, width: 2, inactiveLabel: false, canChangeIcon: false) {
            state "offcyan", label:"cyan", action:"cyan", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "oncyan", label:"cyan", action:"cyan", icon:"st.illuminance.illuminance.bright", backgroundColor:"#00FFFF"
        }
        standardTile("magenta", "device.magenta", height: 2, width: 2, inactiveLabel: false, canChangeIcon: false) {
            state "offmagenta", label:"magenta", action:"magenta", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "onmagenta", label:"magenta", action:"magenta", icon:"st.illuminance.illuminance.bright", backgroundColor:"#FF00FF"
        }
        standardTile("orange", "device.orange", height: 2, width: 2, inactiveLabel: false, canChangeIcon: false) {
            state "offorange", label:"orange", action:"orange", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "onorange", label:"orange", action:"orange", icon:"st.illuminance.illuminance.bright", backgroundColor:"#FF6600"
        }
        standardTile("purple", "device.purple", height: 2, width: 2, inactiveLabel: false, canChangeIcon: false) {
            state "offpurple", label:"purple", action:"purple", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "onpurple", label:"purple", action:"purple", icon:"st.illuminance.illuminance.bright", backgroundColor:"#BF00FF"
        }
        standardTile("yellow", "device.yellow", height: 2, width: 2, inactiveLabel: false, canChangeIcon: false) {
            state "offyellow", label:"yellow", action:"yellow", icon:"st.illuminance.illuminance.dark", backgroundColor:"#D8D8D8"
            state "onyellow", label:"yellow", action:"yellow", icon:"st.illuminance.illuminance.bright", backgroundColor:"#FFFF00"
        }
		standardTile("reset", "device.reset", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label:"Reset Color", action:"reset", icon:"st.lights.philips.hue-single"
		}
		standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
		}        
        
        
        
    }

	main(["switch"])
	details(["switch", "levelSliderControl", "rgbSelector",
             "red", "redSliderControl", "redValueTile",
             "green", "greenSliderControl", "greenValueTile",
             "blue", "blueSliderControl", "blueValueTile",
             "white", "whiteSliderControl", "whiteValueTile",
             "fireplace", "storm", "deepfade",
             "litefade", "police",
             "refresh" ])
}

def installed() {
	configure()
}
def updated() {
	//response(refresh())
    configure()
    //getDeviceData()
}
def configure() {
	log.debug "Configuring Device For SmartThings Use"
    
    sendEvent(name: "redLevel", value: 99)
    sendEvent(name: "greenLevel", value: 99)
    sendEvent(name: "blueLevel", value: 99)
    sendEvent(name: "whiteLevel", value: 99)
    
    def cmds = []
    cmds << zwave.associationV2.associationSet(groupingIdentifier:5, nodeId:[zwaveHubNodeId]).format()

    delayBetween(cmds, 500)
    
}
def parse(description) {
	def result = null
	if (description != "updated") {
		def cmd = zwave.parse(description, [0x20: 1, 0x26: 2, 0x70: 2, 0x72: 2, 0x60: 3, 0x33: 2, 0x32: 2, 0x31:2, 0x30: 2, 0x86: 1, 0x7A: 1])

        if (cmd) {
			result = zwaveEvent(cmd)
			log.debug("zwaveEvent parsed to $result")
		} else {
			log.debug("Couldn't parse '$description'")
		}
	}
	result
}

def on() {
	log.debug "on()"
	//sendEvent(name: "switch", value: "on")
    log.info "running program: ${state.runningProgram}"
    log.info "colors are zeros is: ${colorsAreZeros()}"
    if ( state.runningProgram ) {
    	turnProgramOn(state.runningProgram.programName, state.runningProgram.programNumber)
    } else if ( state.previousHexLevels && !colorsAreZeros()) {
    	log.info "Recalling previous color settings: ${state.previousHexLevels}"
    	resetToPreviousLevels(state.previousHexLevels)
    } else {
		delayBetween([zwave.basicV1.basicSet(value: 0xFF).format(), 
    			  	  zwave.switchMultilevelV1.switchMultilevelGet().format(),
                      configure(),
                      getDeviceData()
                      ], 5000) 
    }
}
def off() {
	log.debug "off()"
	sendEvent(name: "switch", value: "off", displayed: true, isStateChange: true)
    toggleOffProgramTiles("allOfThem")
    toggleOffColorTiles()
	delayBetween ([zwave.basicV1.basicSet(value: 0x00).format(), zwave.switchMultilevelV1.switchMultilevelGet().format()], 5000)
}
def setLevel(level) {
	setLevel(level, 1)
}
def setLevel(level, duration) {
	log.debug "setLevel() level = ${level}"
	if(level > 99) level = 99
	commands([
		zwave.switchMultilevelV3.switchMultilevelSet(value: level, dimmingDuration: duration),
		zwave.switchMultilevelV3.switchMultilevelGet(),
	], (duration && duration < 12) ? (duration * 1000) : 3500)
}
def setSaturation(percent) {
	log.debug "setSaturation($percent)"
	setColor(saturation: percent)
}
def setHue(value) {
	log.debug "setHue($value)"
	setColor(hue: value)
}
def setColor(value) {
	def result = []
	log.debug "setColor: ${value}"
        
	if (value.hex) {
    	log.debug "setting color with hex"
		def c = value.hex.findAll(/[0-9a-fA-F]{2}/).collect { Integer.parseInt(it, 16) }
        result << zwave.switchColorV3.switchColorSet(red:c[0], green:c[1], blue:c[2])
	} else {
		log.debug "setting color with hue & saturation"
        def hue = value.hue ?: device.currentValue("hue")
		def saturation = value.saturation ?: device.currentValue("saturation")
		if(hue == null) hue = 13
		if(saturation == null) saturation = 13
		def rgb = huesatToRGB(hue as Integer, saturation as Integer)
        def whiteValue = device.currentValue("colorTemperature")
        result << zwave.switchColorV3.switchColorSet(red: rgb[0], green: rgb[1], blue: rgb[2])
	}

	if(value.hue) sendEvent(name: "hue", value: value.hue, displayed: false)
	if(value.hex) sendEvent(name: "color", value: value.hex, displayed: false)
	if(value.switch) sendEvent(name: "switch", value: value.switch, displayed: false)
	if(value.saturation) sendEvent(name: "saturation", value: value.saturation, displayed: false)

	commands(result)
}
def setColorTemperature(percent) {
	log.debug "setColorTemperature Percent: ${percent}"
    
    if ( percent >= 1 )
    	sendEvent(name: "white", value: "onwhite", descriptionText: "White Channel is 'ON'", isStateChange: true)
    else
    	sendEvent(name: "white", value: "offwhite", descriptionText: "White Channel is 'OFF'", displayed: false, isStateChange: true)
    
	if(percent > 99) percent = 99
	int warmValue = percent * 255 / 99

    command(zwave.switchColorV3.switchColorSet(warmWhite:warmValue, coldWhite:(255 - warmValue)))
}

def reset() {
	log.debug "reset()"
	sendEvent(name: "color", value: "#ffffff")
	setColorTemperature(99)
}
def refresh() {
	commands([
		zwave.switchMultilevelV3.switchMultilevelGet(),
	], 1000)
}

def redOn() {  
    def color = "red"
    def value = 99
	log.debug "setting ${color} to ${value}"
    sendEvent(name: color, value: "on")
    sendEvent(name: "${color}Level", value: value)
    setRedLevel(value)
}
def redOff() {
    def color = "red"
    def value = 0
	log.debug "setting ${color} to ${value}"
    sendEvent(name: color, value: "off")
    sendEvent(name: "${color}Level", value: value)
    setRedLevel(value)
}
def setRedLevel(value) {
	toggleOffProgramTiles(value)
	log.debug "setRedLevel: ${value}"
    def level = Math.min(value as Integer, 99)    
    level = 255 * level/99 as Integer
	log.debug "level: ${level}"
	if ( value > 0 ) {
    	if (device.latestValue("switch") == "off") { on() }
        sendEvent(name: "red", value: "on")
    } else {
    	sendEvent(name: "red", value: "off")
    }
    def redLevelNew = Math.min(value as Integer, 99)
    redLevelNew = 255 * redLevelNew/99 as Integer
    def greenLevelNew = Math.min(device.latestValue("greenLevel") as Integer, 99)
    greenLevelNew = 255 * greenLevelNew/99 as Integer    
    def blueLevelNew = Math.min(device.latestValue("blueLevel") as Integer, 99)
    blueLevelNew = 255 * blueLevelNew/99 as Integer    
    def hexColorNew = "#${hex(redLevelNew)}${hex(greenLevelNew)}${hex(blueLevelNew)}"
    setColor([hex: hexColorNew.toUpperCase()])
}
def greenOn() {
    def color = "green"
    def value = 99
	log.debug "setting ${color} to ${value}"
    sendEvent(name: color, value: "on")
    sendEvent(name: "${color}Level", value: value)
    setGreenLevel(value)
}
def greenOff() {
	def color = "green"
    def value = 0
	log.debug "setting ${color} to ${value}"
    sendEvent(name: color, value: "off")
    setGreenLevel(value)
}
def setGreenLevel(value) {
	toggleOffProgramTiles(value)
	log.debug "setGreenLevel: ${value}"
    def level = Math.min(value as Integer, 99)    
    level = 255 * level/99 as Integer
	log.debug "level: ${level}"
	if ( value > 0 ) {
    	if (device.latestValue("switch") == "off") { on() }
        sendEvent(name: "green", value: "on")
    } else {
    	sendEvent(name: "green", value: "off")
    }
    def redLevelNew = Math.min(device.latestValue("redLevel") as Integer, 99)
    redLevelNew = 255 * redLevelNew/99 as Integer
    def greenLevelNew = Math.min(value as Integer, 99)
    greenLevelNew = 255 * greenLevelNew/99 as Integer    
    def blueLevelNew = Math.min(device.latestValue("blueLevel") as Integer, 99)
    blueLevelNew = 255 * blueLevelNew/99 as Integer    

    def hexColorNew = "#${hex(redLevelNew)}${hex(greenLevelNew)}${hex(blueLevelNew)}"
    log.info "New Hex Color Code: ${hexColorNew.toUpperCase()}"

    setColor([hex: hexColorNew.toUpperCase(), isTileCommand: true])     
}
def blueOn() {
	def color = "blue"
    def value = 99
	log.debug "setting ${color} to ${value}"
    sendEvent(name: color, value: "on")
    setBlueLevel(value)
}
def blueOff() {
	def color = "blue"
    def value = 0
	log.debug "setting ${color} to ${value}"
    sendEvent(name: color, value: "off")
    setBlueLevel(value)
}
def setBlueLevel(value) {
	toggleOffProgramTiles(value)
	log.debug "setBlueLevel: ${value}"
    def level = Math.min(value as Integer, 99)    
    level = 255 * level/99 as Integer
	log.debug "level: ${level}"
	if ( value > 0 ) {
    	if (device.latestValue("switch") == "off") { on() }
        sendEvent(name: "blue", value: "on") 
    } else {
    	sendEvent(name: "blue", value: "off")
    }
    def redLevelNew = Math.min(device.latestValue("redLevel") as Integer, 99)
    redLevelNew = 255 * redLevelNew/99 as Integer
    def greenLevelNew = Math.min(device.latestValue("greenLevel") as Integer, 99)
    greenLevelNew = 255 * greenLevelNew/99 as Integer    
    def blueLevelNew = Math.min(value as Integer, 99)
    blueLevelNew = 255 * blueLevelNew/99 as Integer    

    def hexColorNew = "#${hex(redLevelNew)}${hex(greenLevelNew)}${hex(blueLevelNew)}"
    log.info "New Hex Color Code: ${hexColorNew.toUpperCase()}"

    setColor([hex: hexColorNew.toUpperCase(), isTileCommand: true])       
}
def whiteOn() {
	log.debug "whiteOn()"
	sendEvent(name: "white", value: "on", displayed: true, descriptionText: "White Channel is 'ON'", isStateChange: true)
    def channel = 0
	def whiteLevel = hex(255)
    def cmd = [String.format("3305010${channel}${whiteLevel}%02X", 50)]
    cmd
}
def whiteOff() {
	log.debug "whiteOff()"
	sendEvent(name: "white", value: "off", displayed: true, descriptionText: "White Channel is 'OFF'", isStateChange: true)
	def channel = 0
	def whiteLevel = hex(0)
    setWhiteLevel(0)
    def cmd = [String.format("3305010${channel}${whiteLevel}%02X", 50)]
    cmd
}
def setWhiteLevel(value) {
	log.debug "setwhiteLevel: ${value}"
    def level = Math.min(value as Integer, 99)    
    level = 255 * level/99 as Integer
	log.debug "level: ${level}"
	if ( value > 0 ) {
    	if (device.latestValue("switch") == "off") { on() }
        sendEvent(name: "white", value: "on")
    } else {
    	sendEvent(name: "white", value: "off")
    }
    def channel = 0
	def whiteLevel = hex(level)
    def cmd = [String.format("3305010${channel}${whiteLevel}%02X", 50)]
    cmd    
}

def fireplaceOn() {
	log.debug "fireplaceOn()"
    turnProgramOn("fireplace", 6)
}
def fireplaceOff() {
	log.debug "fireplaceOff()"
    turnProgamOff("fireplace")
}
def stormOn() {
	log.debug "stormOn()"
	turnProgramOn("storm", 7)
}
def stormOff() {
	log.debug "stormOff()"
	turnProgamOff("storm")
}
def deepfadeOn() {
	log.debug "deepfadeOn()"
	turnProgramOn("deepfade", 8)
}
def deepfadeOff() {
	log.debug "deepfadeOff()"
	turnProgamOff("deepfade")
}
def litefadeOn() {
	log.debug "litefadeOn()"
	turnProgramOn("litefade", 9)
}
def litefadeOff() {
	log.debug "litefadeOff()"
	turnProgamOff("litefade")
}
def policeOn() {
	log.debug "policeOn()"
	turnProgramOn("police", 10)
}
def policeOff() {
	log.debug "policeOff()"
	turnProgamOff("police")
}

def red() { 
    def color = "red"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def green() { 
    def color = "green"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def blue() { 
    def color = "blue"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def white() {
    def color = "coldWhite"
	log.debug "turning on white()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(255))
}
def cyan() {
	def color = "cyan"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def magenta() {
	def color = "magenta"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def orange() {
	def color = "orange"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def purple() {
	def color = "purple"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def yellow() {
	def color = "yellow"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def pink() {
	def color = "pink"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def coldWhite() {
	def color = "coldWhite"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def warmWhite() {
	def color = "warmWhite"
	log.debug "turning on ${color}()"
    def rgbColor = colorNameToRgb(color)
    def hexColor = rgbToHex(rgbColor)
    setColor(hex: hexColor)
    sendRGBW(hex(rgbColor.r), hex(rgbColor.g), hex(rgbColor.b), hex(0))
}
def fireplace() { fireplaceOn() }
def storm() { stormOn() }
def deepfade() { deepfadeOn() }
def litefade() { litefadeOn() }
def police() { policeOn() }

def getCurrentHexLevels() {
    def redLevelNew = Math.min(device.latestValue("redLevel") as Integer, 99)
    redLevelNew = 255 * redLevelNew/99 as Integer
    def greenLevelNew = Math.min(device.latestValue("greenLevel") as Integer, 99)
    greenLevelNew = 255 * greenLevelNew/99 as Integer    
    def blueLevelNew = Math.min(device.latestValue("blueLevel") as Integer, 99)
    blueLevelNew = 255 * blueLevelNew/99 as Integer
    def whiteLevelNew = Math.min(device.latestValue("whiteLevel") as Integer, 99)
    whiteLevelNew = 255 * whiteLevelNew/99 as Integer
    def currentHexLevels = ["red":		hex(redLevelNew),
    				   	    "green":	hex(greenLevelNew),
                            "blue":		hex(blueLevelNew),
                            "white":	hex(whiteLevelNew)]                            
	return currentHexLevels
}
def resetToPreviousLevels(values) {
	log.debug "resetToPreviousLevels with ${values}"
	def hexColorPrevious = "#${values.red}${values.green}${values.blue}"
    def colorString = getColorDataFromHex(hexColorPrevious)

    if ( Integer.parseInt(values.red,16) > 0 )
    	sendEvent(name: "red", value: "on")

    if ( Integer.parseInt(values.green,16) > 0 )
    	sendEvent(name: "green", value: "on")
    
    if ( Integer.parseInt(values.blue,16) > 0 )
    	sendEvent(name: "blue", value: "on")
        
    if ( Integer.parseInt(values.white,16) > 0 )
    	sendEvent(name: "white", value: "on")
        
	//for some reason we have a race condition with setColor and setWhiteLevel. Can't have both
    //setColor(colorString)
    //set the white channel to what it was
    //setWhiteLevel(values.white)
    
    //using raw device command because of issue above. When device reports back it will set sliders
    sendRGBW(values.red,values.green,values.blue,values.white)
}
def turnProgramOn(programName, programNumber) {
	log.debug "Turning ${programName} On"
    state.runningProgram = ["programName": programName, "programNumber": programNumber]
    state.previousHexLevels = getCurrentHexLevels()
	sendEvent(name: programName, value: "on")
    toggleOffProgramTiles(programName)
    toggleOffColorTiles()
    if ( device.latestValue("switch") == "off" )
    	sendEvent(name: "switch", value: "on")
    updateZwaveParam([paramNumber:72, value:programNumber,  size:1])
}
def turnProgamOff(programName) {
	sendEvent(name: programName, value: "off")
    state.runningProgram = null
    log.info "previous hex levels: ${state.previousHexLevels}"
    if ( state.previousHexLevels ) {
    	log.info "Previous levels in fireplaceOff is: ${state.previousHexLevels}"
    	resetToPreviousLevels(state.previousHexLevels)
    } else {
    	log.info "No previous levels saved. Setting to all On"
    	resetToPreviousLevels(["red": "FF", "green": "FF", "blue": "FF", "white": "FF"])
    }    
}
def toggleOffProgramTiles(exceptThisTile) {
	def programTiles = ["fireplace", "deepfade", "litefade", "storm", "police"]
    programTiles.each() {
    	if ( it != exceptThisTile )
    		if ( device.latestValue(it) == "on" )
    			sendEvent(name: it, value: "${it}Off", displayed: false, isStateChange: true)
    }
}
def toggleOffColorTiles() {
	sendEvent(name: "red", 		value: "redOff", 	displayed: false, isStateChange: true)
    sendEvent(name: "green", 	value: "greenOff", 	displayed: false, isStateChange: true)
    sendEvent(name: "blue", 	value: "blueOff", 	displayed: false, isStateChange: true)
    sendEvent(name: "white", 	value: "whiteOff",	displayed: false, isStateChange: true)
}
def toggleTiles(pickedColor) {
	log.debug "toggleTiles(${pickedColor})"    
    def colorTiles = ["white","red","green","blue","cyan","magenta","orange","purple","yellow"]
    def programTiles = ["fireplace", "deepfade", "litefade", "storm", "police"]
    def allTiles = colorTiles + programTiles
    def cmds = []
    def description = ""
    
    for ( tile in allTiles ) {
    	description = "RGB Channels set to ${pickedColor.capitalize()}"
    	if ( tile == pickedColor ) { //turn on the tile picked
        	if ( programTiles.any { it == pickedColor } ) {
            	description = "Running Program ${pickedColor.capitalize()}"
            }
            if ( pickedColor == "white" ) {
            	cmds << sendEvent(name: pickedColor, value: "on${pickedColor}", display: false, isStateChange: false )
            } else {
            	cmds << sendEvent(name: pickedColor, value: "on${pickedColor}", display: true, descriptionText: description, isStateChange: true)
            }
        } else if ( pickedColor == "white" ) {
        	for ( program in programTiles ) {
                cmds << sendEvent(name: program, value: "off${program}", displayed: false, isStateChange: true)
            }              
        } else {
			//if tile is a program, turn off all other tiles
            if ( programTiles.any { it == pickedColor } ) {
            	cmds << sendEvent(name: tile, value: "off${tile}", displayed: false, isStateChange: true)
            }
            //if tile is a color, turn off all other color tiles but white
            /*if ( colorTiles.any { it == pickedColor } ) {
            	if (( pickedColor != "white" ) && ( tile != "white" )) {
            		cmds << sendEvent(name: tile, value: "off${tile}", displayed: false, isStateChange: true)
                }
            }*/
        }
    }    
    delayBetween(cmds, 2500)
}
def colorsAreZeros() {
	def result = true
    def redVal = 	device.latestValue("redLevel") as Integer
    def greenVal =  device.latestValue("greenLevel") as Integer
    def blueVal = 	device.latestValue("blueLevel") as Integer
    def whiteVal =  device.latestValue("whiteLevel") as Integer
    log.info "total colors = ${redVal + greenVal + blueVal + whiteVal}"
    if ( redVal + greenVal + blueVal + whiteVal )
    	result = false
    result
}
def getColorDataFromHex(colorHex) {
	log.debug "getColorDataFromHex: ${colorHex}"
    
    def colorRGB = hexToRgb(colorHex)
	def colorHSL = rgbToHSL(colorRGB)
        
    def c = [:]
    c = [h: colorHSL.h, 
    			 s: colorHSL.s, 
                 l: device.latestValue("level"), 
                 r: colorRGB.r, 
                 g: colorRGB.g,
                 b: colorRGB.b,
                 rh: hex(colorRGB.r),
                 gh: hex(colorRGB.g),
                 bh: hex(colorRGB.b),
                 hex: colorHex,
                 alpha: 1]
     
     def newValue = ["hex": c.hex, "hue": c.h, "saturation": c.s, "level": c.l, "red": c.r, "green": c.g, "blue": c.b, "alpha": c.alpha]
     return newValue
}
def rgbToHSL(rgb) {
	def r = rgb.r / 255
    def g = rgb.g / 255
    def b = rgb.b / 255
    def h = 0
    def s = 0
    def l = 0
    
    def var_min = [r,g,b].min()
    def var_max = [r,g,b].max()
    def del_max = var_max - var_min
    
    l = (var_max + var_min) / 2
    
    if (del_max == 0) {
            h = 0
            s = 0
    } else {
    	if (l < 0.5) { s = del_max / (var_max + var_min) } 
        else { s = del_max / (2 - var_max - var_min) }

        def del_r = (((var_max - r) / 6) + (del_max / 2)) / del_max
        def del_g = (((var_max - g) / 6) + (del_max / 2)) / del_max
        def del_b = (((var_max - b) / 6) + (del_max / 2)) / del_max

        if (r == var_max) { h = del_b - del_g } 
        else if (g == var_max) { h = (1 / 3) + del_r - del_b } 
        else if (b == var_max) { h = (2 / 3) + del_g - del_r }
        
		if (h < 0) { h += 1 }
        if (h > 1) { h -= 1 }
	}
    def hsl = [:]    
    hsl = [h: h * 100, s: s * 100, l: l]
    
    hsl
}
def hexToRgb(colorHex) {
	def rrInt = Integer.parseInt(colorHex.substring(1,3),16)
    def ggInt = Integer.parseInt(colorHex.substring(3,5),16)
    def bbInt = Integer.parseInt(colorHex.substring(5,7),16)
    
    def colorData = [:]
    colorData = [r: rrInt, g: ggInt, b: bbInt]
    colorData
}
def rgbToHSV(red, green, blue) {
	float r = red / 255f
	float g = green / 255f
	float b = blue / 255f
	float max = [r, g, b].max()
	float delta = max - [r, g, b].min()
	def hue = 13
	def saturation = 0
	if (max && delta) {
		saturation = 100 * delta / max
		if (r == max) {
			hue = ((g - b) / delta) * 100 / 6
		} else if (g == max) {
			hue = (2 + (b - r) / delta) * 100 / 6
		} else {
			hue = (4 + (r - g) / delta) * 100 / 6
		}
	}
	[hue: hue, saturation: saturation, value: max * 100]
}
def huesatToRGB(float hue, float sat) {
	while(hue >= 100) hue -= 100
	int h = (int)(hue / 100 * 6)
	float f = hue / 100 * 6 - h
	int p = Math.round(255 * (1 - (sat / 100)))
	int q = Math.round(255 * (1 - (sat / 100) * f))
	int t = Math.round(255 * (1 - (sat / 100) * (1 - f)))
	switch (h) {
		case 0: return [255, t, p]
		case 1: return [q, 255, p]
		case 2: return [p, 255, t]
		case 3: return [p, q, 255]
		case 4: return [t, p, 255]
		case 5: return [255, p, q]
	}
}
def adjustOutgoingHue(percent) {
	def adjusted = percent
	if (percent > 31) {
		if (percent < 63.0) {
			adjusted = percent + (7 * (percent -30 ) / 32)
		}
		else if (percent < 73.0) {
			adjusted = 69 + (5 * (percent - 62) / 10)
		}
		else {
			adjusted = percent + (2 * (100 - percent) / 28)
		}
	}
	log.info "percent: $percent, adjusted: $adjusted"
	adjusted
}
def setAdjustedColor(value) {
	if (value) {
        log.trace "setAdjustedColor: ${value}"
        def adjusted = value + [:]
        adjusted.hue = adjustOutgoingHue(value.hue)
        // Needed because color picker always sends 100
        adjusted.level = null 
        setColor(adjusted)
    }
}
def sendRGBW(redHex, greenHex, blueHex, whiteHex) {
    def cmd = [String.format("33050400${whiteHex}02${redHex}03${greenHex}04${blueHex}%02X", 100),]
    cmd
}
private hex(value, width=2) {
	def s = new BigInteger(Math.round(value).toString()).toString(16)
	while (s.size() < width) {
		s = "0" + s
	}
	s
}
def rgbToHex(rgb) {
    def r = hex(rgb.r)
    def g = hex(rgb.g)
    def b = hex(rgb.b)
    def hexColor = "#${r}${g}${b}"
    
    hexColor
}
def colorNameToRgb(color) {
	final colors = [  
        [name:"red", 		r: 255, g: 0,	b: 0	],
		[name:"green", 		r: 0, 	g: 255,	b: 0	],
        [name:"blue", 		r: 0, 	g: 0,	b: 255	],
        
		[name:"cyan", 		r: 0, 	g: 255,	b: 255	],
        [name:"magenta", 	r: 255, g: 0,	b: 33	],       
        [name:"orange", 	r: 255, g: 102, b: 0	],
        
        [name:"purple", 	r: 170, g: 0,	b: 255	],
		[name:"yellow", 	r: 255, g: 160, b: 0	],
        [name:"pink",		r: 255, g: 192, b: 203  ],
        
        [name:"coldWhite", 	r: 255, g: 255, b: 255	],
        [name:"warmWhite", 	r: 255, g: 255, b: 185	]        
	]
    
    def colorData = [:]    
    colorData = colors.find { it.name == color }
    
    colorData
}
def getDeviceData() {
    def cmd = []   
    cmd << response(zwave.manufacturerSpecificV2.manufacturerSpecificGet()) 
    cmd << response(zwave.versionV1.versionGet())
	cmd << response(zwave.firmwareUpdateMdV1.firmwareMdGet()) 
    delayBetween(cmd, 2500)
}

private dimmerEvents(physicalgraph.zwave.Command cmd) {
	def value = (cmd.value ? "on" : "off")
	def result = [createEvent(name: "switch", value: value, displayed: false)]
	if (cmd.value) {
		result << createEvent(name: "level", value: cmd.value, unit: "%")
	}
	return result
}
private command(physicalgraph.zwave.Command cmd) {
	if (state.sec) {
		zwave.securityV1.securityMessageEncapsulation().encapsulate(cmd).format()
	} else {
		cmd.format()
	}
}
private commands(commands, delay=200) {
	delayBetween(commands.collect{ command(it) }, delay)
}

def zwaveEvent(physicalgraph.zwave.commands.multichannelv3.MultiChannelCmdEncap cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand([0x20: 1, 0x26: 2, 0x30: 2, 0x31: 2, 0x32: 2, 0x33: 2, 0x70: 2]) // can specify command class versions here like in zwave.parse
	log.info ("Command from endpoint ${cmd.sourceEndPoint}: ${encapsulatedCommand}")
	if ((cmd.sourceEndPoint >= 1) && (cmd.sourceEndPoint <= 5)) { // we don't need color report
    	if ( cmd.sourceEndPoint == 2 ) {
        	sendEvent(name: "redLevel", value: encapsulatedCommand.value)
            if ( encapsulatedCommand.value > 0 ) {
            	sendEvent(name: "red", value: "on")
            } else {
            	sendEvent(name: "red", value: "off")
            }
    	}
        if ( cmd.sourceEndPoint == 3 ) {
        	sendEvent(name: "greenLevel", value: encapsulatedCommand.value)
            if ( encapsulatedCommand.value > 0 ) {
            	sendEvent(name: "green", value: "on")
            } else {
            	sendEvent(name: "green", value: "off")
            }
        }
        if ( cmd.sourceEndPoint == 4 ) {
        	sendEvent(name: "blueLevel", value: encapsulatedCommand.value)
        	if ( encapsulatedCommand.value > 0 ) {
            	sendEvent(name: "blue", value: "on")
            } else {
            	sendEvent(name: "blue", value: "off")
            }
        }
        if ( cmd.sourceEndPoint == 5 ) {
        	sendEvent(name: "whiteLevel", value: encapsulatedCommand.value)
        	if ( encapsulatedCommand.value > 0 ) {
            	sendEvent(name: "white", value: "on")
            }else {
            	sendEvent(name: "white", value: "off")
            }
        }
        def result = "Setting Color Level Sliders from Device Color Level Report"
        return result
    } else {
    	if (encapsulatedCommand) {
			zwaveEvent(encapsulatedCommand)
        }
	}
}
def zwaveEvent(physicalgraph.zwave.commands.configurationv2.ConfigurationReport cmd) {
    return "${device.displayName} parameter '${cmd.parameterNumber}' with a byte size of '${cmd.size}' is set to '${cmd.configurationValue}'"
}
def zwaveEvent(physicalgraph.zwave.commands.switchmultilevelv1.SwitchMultilevelStartLevelChange cmd) {
     log.debug " in multi start"
}
def zwaveEvent(physicalgraph.zwave.commands.switchmultilevelv1.SwitchMultilevelStopLevelChange cmd) {
     //[response(zwave.basicV1.basicGet())]
     log.debug " in multi stop"
}
def zwaveEvent(physicalgraph.zwave.commands.sensormultilevelv2.SensorMultilevelReport cmd) {
    def result = [:]
    if ( cmd.sensorType == 4 ) { //power level comming in
   		result.name = "power"
    	result.value = cmd.scaledSensorValue
    	result.descriptionText = "$device.displayName power usage is ${result.value} watt(s)"
        result.isStateChange
        sendEvent(name: result.name, value: result.value, displayed: false)
    }
    result
}
def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd) {
	dimmerEvents(cmd)
}
def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicSet cmd) {
	dimmerEvents(cmd)
}
def zwaveEvent(physicalgraph.zwave.commands.switchmultilevelv3.SwitchMultilevelReport cmd) {
	dimmerEvents(cmd)
}
def zwaveEvent(physicalgraph.zwave.commands.hailv1.Hail cmd) {
	response(command(zwave.switchMultilevelV1.switchMultilevelGet()))
}
def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityMessageEncapsulation cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand([0x20: 1, 0x84: 1])
	if (encapsulatedCommand) {
		state.sec = 1
		def result = zwaveEvent(encapsulatedCommand)
		result = result.collect {
			if (it instanceof physicalgraph.device.HubAction && !it.toString().startsWith("9881")) {
				response(cmd.CMD + "00" + it.toString())
			} else {
				it
			}
		}
		result
	}
}
def zwaveEvent(physicalgraph.zwave.Command cmd) {
	def linkText = device.label ?: device.name
	[linkText: linkText, descriptionText: "$linkText: $cmd", displayed: false]
}
def zwaveEvent(physicalgraph.zwave.commands.manufacturerspecificv2.ManufacturerSpecificReport cmd) {
    updateDataValue("Manufacturer manufacturerName",	"${cmd.manufacturerName}")
	updateDataValue("Manufacturer manufacturerId", 		"${cmd.manufacturerId}")
    updateDataValue("Manufacturer productId", 			"${cmd.productId}")
    updateDataValue("Manufacturer productTypeId", 		"${cmd.productTypeId}")
    return "Received ManufacturerSpecificReport"
}
def zwaveEvent(physicalgraph.zwave.commands.versionv1.VersionReport cmd) {	
    updateDataValue("Version applicationVersion", 		"${cmd.applicationVersion}")
    updateDataValue("Version applicationSubVersion", 	"${cmd.applicationSubVersion}")
    updateDataValue("Version zWaveLibraryType", 		"${cmd.zWaveLibraryType}")
    updateDataValue("Version zWaveProtocolVersion", 	"${cmd.zWaveProtocolVersion}")
    updateDataValue("Version zWaveProtocolSubVersion",	"${cmd.zWaveProtocolSubVersion}")
    return "Received VersionReport"
}
def zwaveEvent(physicalgraph.zwave.commands.firmwareupdatemdv1.FirmwareMdReport cmd) { 
    updateDataValue("Firmware checksum",		"${cmd.checksum}")
    updateDataValue("Firmware firmwareId",		"${cmd.firmwareId}")
    updateDataValue("Firmware manufacturerId",	"${cmd.manufacturerId}")
    return "Received FirmwareMdReport"
}
def updateZwaveParam(params) {
	if ( params ) {   
        def pNumber = params.paramNumber
        def pSize	= params.size
        def pValue	= [params.value]
        log.debug "Updating ${device.displayName} parameter number '${pNumber}' with value '${pValue}' with size of '${pSize}'"
		def cmds = []
        cmds << zwave.configurationV1.configurationSet(configurationValue: pValue, parameterNumber: pNumber, size: pSize).format()        
        cmds << zwave.configurationV1.configurationGet(parameterNumber: pNumber).format()
        delayBetween(cmds, 1500)        
    }
}
