# LightningSDK

### What is it?

The Lightning SDK is build to be used with FTC robots and the new Java + Android control system that was released for the 2015-2016 competition. The purpose of Lightning is to compensate for the shortcomings of the built-in FTC SDK, and to provide some additional useful functionality that speeds up and simplifies the development of robot code. It is build on top of the FTC SDK, but does not include it, so any code using Lightning will also need a copy of the Robotcore package in the FTC SDK.

Along with all of the source code, in the root folder of this repository, you'll also find [detailed javadocs](javadoc) for all of the code, along with a compiled .jar version of the project. We are working hard to write a functioning wiki for the code, and that will be available through GitHub's Wiki system. The wiki will provide lots of code snippets and explanations on a system basis, instead of the class basis that Javadoc uses.

This is open source software, so feel free to send a pull request if you think you have a way to improve it. We will soon be providing a document with contribution outlines, and that file will be available in this repository. 

The quickest way to communicate with us is to file an issue under GitHub's system. The email address listed for our organization is shared by our entire robotics team, so we will eventually see emails directed to the programming team, but it will take more time. We are unable to provide full-time support for Lightning, but we will try to be as helpful as we can if you're having problems. All feedback is welcome.

An example of how to use this SDK is provided in the "2015-Code" repository in this same organization. Please note that not every feature available in Lightning is used in the example code, but the two projects have been largely developed in parallel.

This entire repository is made available under the GNU General Public License v3.0. A full copy of this license is available as the [LICENSE](LICENSE) file in this repository, or at [gnu.org/licenses](http://www.gnu.org/licenses/).

### Dependencies

All projects that use Lightning, and Lightning itself, will also need FTC's Robotcore library for interfacing with the built-in SDK. A copy of the current version of Robotcore is provided in the 'libs' folder in this repository.
