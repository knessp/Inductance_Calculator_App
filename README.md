# Inductance_Calculator_App
An app for Android that calculates inductance and mutual inductance of arbitrary, 2D, thin shapes.

In the code repository, the file 'Inductance_Calc_App_MainScreen_redacted.java' demonstrates most of the math involved with calculating the inductance and mutual inductance. 

The app uses the discrete Neumann method to find the mutual inductance between two curves. The Neumann method provides an exact solution to the mutual inductance, assuming the curves are infinitely thin. Discretizing the Neumann formula in order to solve for any shapes provides a very accurate approximation to the mutual inductance.

Link to working project: https://play.google.com/store/apps/details?id=com.ookttah.philip.mutualinductance

Link to free version (same functionality but includes ads) of working project: https://play.google.com/store/apps/details?id=com.ookttah.philip.mutualinductanceWithAds
