# To-do List for the Lightning SDK

### ~~Cleanup~~
* ~~Change the op mode hiding functionality to the `OpMode` decorator~~
* ~~Better divide functionality of `SimpleOpMode` and `Robot`~~

#### App Improvements
* ~~Support the `OpMode` annotation~~
* Apply `OpMode` and `Active` annotations to appropriate op modes
* Add categories for op modes
* Add manual controls
* Add material design

#### Documentation
* Update all javadoc
* Add a description of Lightning SDK to the package-info of `lightning`
* Remove unnecessarily commented code
* Remove unnecessary `{@inheritDoc}` tags
* Write better package-info files
* Update the gh-pages branch

#### Encoders
* ~~Test encoder functionality on a single motor~~
* ~~Implement an 'average' encoder value method in `DriveMotorSet` or `DriveSystem`~~
* Make `MecanumDrive` override the average encoder function to account for polarity
* Test encoder functionality on a drive base

#### Event-Based Joystick Input
* ~~Merge master into the joystick branch~~
* ~~Write a test program~~
* ~~Run the test program~~
* Merge the branch into master (to be reviewed by Pranav)

#### Hardware
* Test the CRServo code
* __[Uncertain]__ Write and test code to detect if a servo is attached to a motor

#### More Control Schemes
* ~~Add an abstract control scheme class~~
* Write `TeleOpTest` test programs
* Run the test programs
* Merge the branch into master

#### PID Control
* Write a generic PID control system
* Integrate the PID system to the task scheduler
* Apply the PID system to the drive system
* __[Uncertain]__ Apply the PID system elsewhere

#### Sensor Wrapper (Currently on-hold for the foreseeable future)
* Make `Lightning.getSensor()` return a wrapper
* _Add more things here???_

#### Scripting
* Make a script that compiles all necessary code and copies it to the phone
* Make a script that automaticaly updates the gh-pages branch with javadoc changes on a fixed ratio
* Make a script that automatically corrects style errors on a fixed interval

#### ~~Task Scheduler~~
* ~~Implement a `remove(Runnable)` method~~
* ~~Test the remove method~~

#### Telemetry
* ~~Remove overloads of `Lightning.sendTelemetryData()` and make the `Object` implementation check if the object is an instance of the other parameters~~
* __[Uncertain]__ Write a more robust telemetry system
