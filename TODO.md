# To-do List for the Lightning SDK

### Timeline for development:
* Add a more robust telemetry logging system
* Detect if there is an encoder attached to a motor (4 weeks)
* Change the op mode hiding functionality to the decorator. (4 weeks)
* Make SimpleOpMode more interoperable with a `Robot`. (2 weeks)

#### Task Scheduler
* Test the task scheduler (`io.github.thunderbots.testing.TaskSchedulerTest.java`)
* Implement a `remove(Runnable)` method
* Test the remove method

#### Event-Based Joystick Input
* Merge master into the joystick branch
* Write a test program
* Run the test program

#### PID Control
* Write a generic PID control system
* Integrate the PID system to the task scheduler
* Apply the PID system to the drive system
* __[Uncertain]__ Apply the PID system elsewhere

#### Sensor Wrapper
* Make `Lightning.getSensor()` return a wrapper
* _Add more things here???_

#### More Control Schemes
* Make a branch for this
* Add an abstract control scheme class
* Rewrite the default controls in `TeleOp` to use the control scheme system
* Implement other control schemes in the new system (tank, mecanum, drive/spin, video game, etc.)

#### App Improvements
* Support the `OpMode` annotation
* Add categories for op modes

