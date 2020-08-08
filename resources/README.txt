Specific design choices for project:

We decided to have the controller translate animation information that it gets from the model before sending to the view so the view would not have access to anything that has to do with the model. 
Our purpose for doing this was to promote low-coupling between the view and the model as it would prevent the view from getting access to something such as the List of Shapes from the model and having to make something out of it.
Furthermore, our outlook on this Animation project was that the view would simply get the data it needs and display it properly depending on what kind of view it is. With the controller getting data from the model and manipulating it before sending it to the view, it allows for the view to display that data without caring how the results were produced. We followed this design choice as this is what the MVC lecture described the view as.


Model Design Changes:

-Shape Interface
	-Now have getter methods for name and its list of motions. This allows for the access of these fields for information.
	-Now have methods to add and remove motions from a Shape. This makes the animation more easily modifiable.
	-Now have methods to generate XML-based SVG code to define the shape and its motions/animations. This allows the creation of SVG Views with less repetitive code. AbstractShapes contains a protected method that returns a map to generate the SVG motions for respective Shapes.
	
	-AbstractShapes 
		-Now have a name and a List of Motion rather than fields for shape position, color, and size. This streamlines the ShapeAnimation process as it allows us to add individual shapes to an animation.
	
-IModel Interface
	-Now has the ability to get, add, and remove shapes to allow for the access of the list of Shape, and the modifying of the list of Shape after the animation has been created.
	-Now has the ability to add and remove motions for a particular shape to allow for the modifying the motions of the animation.
	-Now has getters for the canvas x, y, width, and height to allow for the access of this information for creating a view of the specified offset and size.

	-ShapeAnimation 
		-Now has fields for a list of Shapes rather than a list of Motions. This allows for the adding of individual shapes and motions to an animation after the animation has been defined.
		-Now has fields for canvas x,y offset and width and height to allow for resizing the window and the minimum x and y of the window to the specified values.
		-The private checkConstraints() and checkForBadOverlaps() methods have been modified/simplified to work with a list of Shapes rather than a list of Motions.
		-Contains an AnimationBuilder implementation that is used to construct ShapeAnimations by reading from a file.
		
-Motion
	-Now have fields to represent starting and ending tick, x, y, color, width, and height rather than just starting and ending shapes/states and ticks. This simplifies motions and doesn't tie them to particular shapes, which allows for multiple shapes to have the same motions.
	-Starting and ending ticks can now be equal because this was done in the examples given to initialize the shapes in animations.
	-generateDescription() and overlaps() methods have now been modified to work with the new fields.
	-isSameState() has been replaced with the isConsistent() method, which checks if any given motion starts where the last one left off.
	-Now has getters for all of the new fields.
	
	
Controller design:

-Added a controller to get information from the model and translate that information to send to the view for the displaying of animations in Text, Visual, or SVG form. The controller promotes low-coupling between the model and the view.
	-For Text Views, the controller gets information on the canvas offset and size, the shapes in the animation, and the motions for each shape. It then turns it into a sorted String describing the animation to send to the view. The view then prints it into a given text file or the console.
	-For Visual Views, the controller keeps track of the tick and get information from the model about which shapes have motions occurring on the current tick. It then sends a list of graphical (java.awt) Shapes and a list of respective Colors to the view for a visual display of the animation.
	-For SVG Views, the controller gets the information about each shape and their animations in the model and translates it into an XML-based text description of the animation, which is then sent to the view for the creation of an SVG file to display the animation in a browser.
	
-IController Interface
	-Has method for sending an XML-based String of the animation and a given location to a SVGView for the creation of an SVG file or console output.
	-Has method for sending a text description of the animation and a given location to a TextView for the creation of a Text file or console output.
	-Has method startAnimation() that uses a timer, to continually increment ticks at the given tempo. At each tick, it sends the shapes which have motions at the tick to a VisualView to be drawn and displayed in a window representing a visual view of the animation.
	-Has methods to get and set the tempo for working with the speed of the animation for visual and svg views.
	
	-ExcellenceController
		-Has private helper method findLastTick() to let the Timer know when to stop.
		-Has private helper methods findShapesToDraw() and interpolate() to assist in the sending of information to VisualViews.
		

View Design:

-Added three views, VisualView, TextView, and SVGView, to generate an Excellence animation in various forms.
	-Visual Views get the list of current shapes and a list of their respective colors to be drawn for a particular frame. It then sends them to a ViewPanel (that extends JPanel) to be painted on a scrollPanel in a window.
	-Text Views get a text description of the animation from the controller and sends it to a text file or the console if the given location is invalid or empty.
	-SVG Views get a XML-based description of an animation from the controller and generates an SVG file to a given location or to the console if the location is empty or invalid.
	
-IView Interface
	-Has method to generateSVG(), which takes a location and XML-based text to generate an svg file to the location or the console.
	-Has method printTextView(), which takes a location and a text description of an animation to create a text file in the given location or the console.
	-Has method addShapes(), which takes a list of graphical (java.awt) Shapes and a list of their respective Colors and sends them to a ViewPanel to be drawn.
	-Has method setCanvasSize(), which takes the canvas offset x and y and the width and height to configure the size of the animation panel in the JFrame.
	-Has method refresh(), which simply repaints the animation's state for the current frame.
	-Has method makeVisible(), which simply calls setVisible(true) to make the animation visible for when we start the animation.
	
	-AbstractView
		-Having this class allows for the usage of the factory pattern through implementing a static FactoryView subclass that creates a new View based on the command-line input of the main method.
		-Implements all methods in IView to be default unsupported throwing UnsupportedOperationExceptions. Extends JFrame for the creation of VisualView visual frames for the animation.
	
		-VisualView
			-Overrides the addShapes(), setCanvasSize(), refresh(), and makeVisible() methods to support them by utilizing them to create a visual display of the animation.
			-ViewPanel
				-implements JPanel, overriding paintComponent() to paint all of the shapes and their proper colors to the panel.
				-Has method addShapes(), which takes in a list of graphical Shapes and a list of their respective colors to be drawn.
		-TextView
			-Overrides the createTextView() method to support it by creating a text file with the given text description of the animation, or outputting it to the console.
		-SVGView
			-Overrides the generateSVG() method to support it by generating an SVG file with the given XML-based text of the animation, or outputs it to the console.
		
		
-Excellence (Main method)
	-Used to run the animation in a variety of views. Takes in command-line inputs and parses them to determine the characteristics of the view.
		