# To-do List for the Lightning SDK

### Cleanup
* Change the op mode hiding functionality to the `OpMode` decorator
* ~~Better divide functionality of `SimpleOpMode` and `Robot`~~

#### App Improvements
* Support the `OpMode` annotation
* Add categories for op modes
* Add manual controls

#### Documentation
* Update all javadoc
* Remove unnecessarily commented code
* Remove unnecessary `{@inheritDoc}` tags
* Write better package-info files
* Update the gh-pages branch

#### Encoders
* Test encoder functionality on a single motor
* Implement an 'average' encoder value method in `DriveMotorSet` or `DriveSystem`
* Test encoder functionality on a drive base

#### Event-Based Joystick Input
* ~~Merge master into the joystick branch~~
* ~~Write a test program~~
* Run the test program
* Merge the branch into master

#### Hardware
* Write a test program for a Servo
* Write a test program for a CRServo
* Write a test program for a Motor
* Test the CRServo code
* __[Uncertain]__ Write and test code to detect if a servo is attached to a motor

#### More Control Schemes
* ~~Make a branch for this~~
* ~~Add an abstract control scheme class~~
* ~~Rewrite the default controls in `TeleOp` to use the control scheme system~~
* ~~Implement other control schemes in the new system (tank, mecanum, drive/spin, video game, etc.)~~
* Write a test program for the new system with drive-spin controls
* Write a test program for the new system with tank-style controls
* Run the test programs
* Merge the branch into master

#### PID Control
* Write a generic PID control system
* Integrate the PID system to the task scheduler
* Apply the PID system to the drive system
* __[Uncertain]__ Apply the PID system elsewhere

#### Sensor Wrapper
* Make `Lightning.getSensor()` return a wrapper
* _Add more things here???_

#### Scripting
* Make a script that compiles all necessary code and copies it to the phone
* Make a script that auto-updates the gh-pages branch with javadoc changes
* Make a script that auto-corrects style errors on a fixed interval

#### Task Scheduler
* Test the task scheduler (`io.github.thunderbots.testing.TaskSchedulerTest.java`)
* Implement a `remove(Runnable)` method
* Test the remove method

#### Telemetry
* Remove overloads of `Lightning.sendTelemetryData()` and make the `Object` implementation check if the object is an instance of the other parameters
* __[Uncertain]__ Write a more robust telemetry system
