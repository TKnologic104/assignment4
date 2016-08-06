-> A description of your code and graphics, and it might include diagrams.

For project4b we added look, look2 support to our Model, added a GUI interface using javaFX for our View and Controller aspects. 

The Model Component for 4b added the look and look2 methods. They are implemented in our Tribble and FraidyCat classes. For Tribble, the doTimeStep method invokes look. The Tribble will examine the spaces distance 1 around him and look for either a another Tribble to reproduce with or an empty space to walk to. For FraidyCat, the fight method invokes look2 to examine spaces distance 2 to look for an empty space to run into, otherwise FraidyCat will fight and the resolveEncounter will continue.

The View Component for 4b is triggered by the displayWorld static method in Critter. The displayWorld method adds the facility to be able to add graphic contents to the canvas, via the Graphic Context associated with our Canvas. We first clear the canvas by painting an empty rectangle at the starting corner. The drawShapes method is then called and iterates through the critter population to draw critters depending on their viewColor, viewOutlineColor, viewFillColor, and viewShape methods. The shapes are defined to scale with the Params.world_width and Params.world_height fields. The stats are also updated on the top of the world in this method. Animation was done using Timeline.

The Controller Component was largely rewritten so that changes would be made via text fields, combo boxes, buttons, and radio buttons in the GUI. We used eventHandlers to map to the various inputs.