Changes:

-Changed SVGView's and TextView's methods to write to Appendables. This allows for a variety of output locations and cleaner code.

-Created a AbstractController and implemented different versions of IController for each type of view (SVG, Text, and Visual/Editor). This makes sure that each controller only has methods that pertain to its specific view and doesn't have unrelated methods.
	-We created a ControllerFactory in AbstractController that creates a controller based off of the provided view type in the command line parameters. This replaces the need for our previous ViewFactory as this factory now creates both a controller and a view.
		-In the main method, we use this factory to create controllers based off of the given command line inputs.

-Added an IMotion interface to allow for Motions with single keyframes, representing a motion that doesn't change any attributes of a shape. It also allows for traditional Motions with two keyframes of different before and after states.

-Added a Keyframe class that represents the state of a Shape at a given tick. This allows for the adding, removing, and editing of keyframes in the EditorView.
	-Motions contain either one or two keyframes to represent before and after states. This allows them to be compatible with the new idea of keyframes.
	-We changed the design of all Motion methods to accommodate for the keyframe design.
	
-In our AnimationBuilder implementation, we added an implementation of the addKeyframe() method which simply adds a keyframe with the given attributes to the shape with the given name.

-In AbstractShape, we added methods to get, add, edit, and delete keyframes to enable compatibility with our new keyframe based design.
	-Also added a removeMotions() helper method, used to remove a given list of motions from a shape.
	
-Changed our timer field in VisualController to be non-final so that the timer can be cancelled and recreated. This allows for the implementation of pausing, playing, resuming, restarting, etc. features to the EditorView.

-Created an initializeAnimation() method that simply sets the canvas of a visual/editor view.


Added:

-Added an EditorView implementation of IView that is able to display a visual portrayal of the animation as well as modify the animation and its playback. This uses a variety of swing components to create an interactive GUI that allows for these features.

-Added isLooping, isPlaying to the VisualController to know when the animation is playing or paused and whether or not it should loop when it reaches the end. This gives us the ability to implement these features for EditorViews.

-Added an IFeatures interface. These are features that are used in the callback action listeners for the interactive swing components in the EditorView GUI. This promotes a low coupling and high level events for telling what the buttons, spinners, etc do in the EditorView. 
	-VisualController implements IFeatures, and any methods that it contains.
	
-Added methods to IModel to get keyframes for a given shape and to get a shape with a given name. This reduces code duplication in VisualController when working with keyframes.
	
-Added method to interpolating a motion at a given tick. Returns the keyframe at that particular tick with the interpolated attributes for values. This allows for the adding of new keyframes to an Animation without changing the nature of the Animation.

-Added a new command line input "edit" for -view in the main method to allow for creating EditorViews through the main method.

-When adding keyframes to an animation through an EditorView, if a keyframe is added before the first keyframe, it simply takes the attributes of the previously first keyframe. Similarly, if a keyframe is added after the last keyframe, it takes the attributes of the previously last keyframe. If a keyframe is added between two other keyframes, it gets the interpolated attributes at that particular tick.


Removed:

-Removed the ViewFactory because the ControllerFactory now does its job and more.

-Removed the single controller implementation of IController in favor of splitting them into multiple implementations to control each type of view.
