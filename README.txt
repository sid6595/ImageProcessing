enumpackage - These are all of the enums used in the program.
    Brightness - Value, Intensity, Luma
    Component - Red, Green, Blue
    Orientation - Horizontal, Vertical


controller (complete) - This controls all commands and methods that manipulate the image.
    commands - These are the commands used in the controller
        Brighten - This Class brightens the picture.
        Flip - This class flips the picture depending on what orientation provided.
        Load - This class will load the named picture.
        Save - This class will save the named picture.
        VisIntensity - This class will create an image that visualizes the
                       value of the rgb of the image.
        VisRGB - This class will create a new image based on the visuals
                 of each individual RBG value.
	Blur - This class will blur the named picture.
	Sharpen - This class will sharpen the named picture.
	SepiaTone - This class will create a sepia version of the named picture.
	Greyscaling - This class will create a greyscaled version of the named picture. 
    FunctionObjects - This interface represents commands that will be given to images.
    ImageController - This interface represents where we store the methods for the controller.
    SimpleController - This class is the controller where all the commands are stored
                       and where everything can be run.

model (complete) - This contains all things to do with the model of an image.
    ImageProcessorModel - This interface represents the location where images will be stored.
    ModelImpl - This class is where the loaded images are store and where the new
                images will be stored.
    Picture - This class represents an image and contains all the methods to modify that image.
    Pixel - This class represents a single pixel in an image.
view (NOT complete) - This was to be able to visualize our tests.
		We still need to make the GUI for the user to be able to use. 
    ImageTextView - This will put the console to visualize the test.
    TextViewInterface - This is the interface to manipulate the console.

Design Changes - Last assignment the main method was in the controller. 
We moved it into its own class and made the controller have an execute command. 
We did this so that we could test the inputs that are passed through our controller. 
It makes our design more robust and makes sure there can be less bugs in the product. 

