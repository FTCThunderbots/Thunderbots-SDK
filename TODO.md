# To-do List for the Lightning SDK

#### Post-Competition
* Add strafe ticks and traverse ticks to mecanum drive
* Deprecate all strafe and traverse stuff in tank drive
* Move motor to an interface and implement it as a dc motor or cr servo

#### App Improvements
* Add categories for op modes
* Add manual controls
* Add material design

#### Documentation
* Add a description of Lightning SDK to the package-info of `lightning`
* Write better package-info files
* Update the gh-pages branch

#### Encoders
* Calibrate stuff to inches / ticks

#### Hardware
* Test the CRServo code
* __[Uncertain]__ Write and test code to detect if a servo is attached to a motor

#### PID Control
* Write a generic PID control system
* Integrate the PID system to the task scheduler
* Apply the PID system to the drive system
* __[Uncertain]__ Apply the PID system elsewhere

#### Scripting
* Make a script that compiles all necessary code and copies it to the phone
* Make a script that automaticaly updates the gh-pages branch with javadoc changes on a fixed ratio
* Make a script that automatically corrects style errors on a fixed interval
