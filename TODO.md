# To-do List for the Lightning SDK

* Fix MathUtil.scaleToRange(); right now it always returns zero.
* (On-hold) Add system for logging to the telemetry
* Remove GamepadButton
* Remove the updating of HardwareMap and Telemetry through `Lightning.initializeRobot`
* Support more control methods
* Support teleop / auto annotations
* Detect if there is an encoder attached to a motor
* Negate motor reversals in DriveMotorSet
* Change the op mode hiding functionality to the decorator.
* Make SimpleOpMode more interoperable with a `Robot`.
* Remove OpModeVariableMonitor, and have LightningOpMode register itself with Lightning.
* Remove some debug information from the telemetry.
