# MEETING LOG

## [07-24-2020]
- **Done**
	- Threaded implementation for all components.
	- Process of generating data and deserialization are automatic, with an interval of 3000ms.
	- Sensors and ISS implementation. *We continue to make small changes here if necessary*.
	- Structure of GUI's source code is defined.
- **Partially or nearly done**
	- GUI's design.
	- Interaction between GUI's components.
	- Main/secondary buttons and functionality implementations.
- **Not done**
	- Unit test.
	- Checkstyle and PMD.
	- Demo and test video.

	
## [07-17-2020]
- **Done**
	- Job assignment:
		1. Hung Vu: Gui's model and controller.
		2. Shane and Tommy: Sensors and ISS software.
		3. Khue and My: GUI's view.
	- Approach for this project:
		1. Sensors and ISS software will operate on their own threads.
		2. GUI's controller will receive and deserialize data, then store them into GUI's model. GUI's view will be updated accordingly.
		3. Network interface **will not** be used.
		4. GUI section will run on its own threads.
- **Not done**
	- Buttons' secondary functions implementation plan.
	- Testing plan.

## [07-13-2020]
- **Done**
    - Setting up working environment for group members (local repo).
    
- **Partially done**
    - Job assignment.
    - Approach for this project.

- **Current plan**: *Subject to change*
    - The project will have an approach by re-using some components in project 1.
        1. Sensors-related components: Tommy's code.
        2. ISS software component: Shane's code.
        3. The code will be changed to fit this project.
    - GUI design for console: My, Hung, Khue.
    - Note: The approach may be concluded using UML diagram.