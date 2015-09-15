# To-do List for the Lightning SDK

* Fix MathUtil.scaleToRange(); right now it always returns zero.
* Implement scaleJoystickInput() in TGamepad
* (On-hold) Add system for logging to the telemetry
* Remove GamepadButton
* Rename TServo to something
* Rename TMotor to Motor
* Make the gamepads in `Lightning` exist in an array or in a list.
* Remove the updating of HardwareMap and Telemetry through `Lightning.initializeRobot`
* Support more control methods
* Support teleop / auto annotations
* Detect if there is an encoder attached to a motor
* Negate motor reversals in DriveMotorSet
* Remove `RobotcoreServo`
* Find out how often the gamepad values are updated in the opmode. If this is infrequent, `OpModeVariableMonitor` can be fully removed.
