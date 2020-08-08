-Added a scrubber design to the EditorView.
	
	-EditorView
		-we added a JSlider to represent the scrubber for the animation. We set it to default to disabled until the animation is played via the play button. When the animation is restarted, the scrubber bar gets redisabled.
		-we linked the value of the spinner representing the tick to add a new keyframe with the value of the scrubber so that it changes with the scrubber.
		-we initialize the scrubber to have a minimum value of 0 and a maximum value of the last tick in the animation, and whenever a new keyframe with a larger tick than the last tick is added, the max value of the scrubber is changed to it.
		-when scrubber is interacted with, the animation pauses until the client clicks the resume button to resume the animation from the tick that the scrubber was left at. The pause button gets disabled and the resume button gets enabled as soon as the scrubber is clicked.
		-made fields private, final, or local variables as appropriate.
	
	-IView
		-added an initializeScrubber() method that uses that last tick of the animation to set the maximum value of the scrubber.
		-added an updateScrubber() method that updates the value of the scrubber based on the current tick of the animation.

	-IFeatures
		-added an initializeScrubber() method that sends the last tick of the animation to the view for the initializing of the scrubber.
		-added an updateTick() method that updates the animation's tick based on the value of the scrubber.
		