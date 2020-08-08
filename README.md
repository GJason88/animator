# Animator
Animator application that allows for the creation and customization of different shapes and objects as well as their respective keyframes within the animation; and the control over the playback of the animation itself.

## Installation
Clone the repository or install the zip to your PC

## Usage
Open terminal (such as command prompt) in resources folder.
Type 'animator.jar' followed by desired parameters  
Params:  
&nbsp;&nbsp;-in "filename.txt" (the input animation file) *required  
&nbsp;&nbsp;-view "text", "svg", "visual", or "edit" *required   
&nbsp;&nbsp;-out "filename.txt" (the output animation file for textual and SVG view types) *use for text and svg views  
&nbsp;&nbsp;-speed X (an integer to set the inital speed in ticks per second for visual and editor views) *default=15

Example: "C:\Users\NAME\Desktop\Animator\resources animator.jar -in buildings.txt -view edit -speed 30"

## Contributing
Feel free to send a pull request.

## License
[MIT](https://choosealicense.com/licenses/mit/)
