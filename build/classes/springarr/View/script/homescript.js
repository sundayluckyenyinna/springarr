/* Copy write 2021.All rights reserved|Springarr 
 * Author: Sunday Lucky Enyinna
 */

// get all list elements 
	var listArray = document.body.getElementsByTagName('li');
	// change the cursor type to pointer of all
	for(var list of listArray)
	{
       	    list.style = "cursor: pointer;";
	}
/* This code changes the background images of the body as the page loads 
 * 
 */             
// create an array to store all the images 
    var imageArray = new Array();
    var numberOfImagesInGallery = 39;
    for (var i = 1; i <= numberOfImagesInGallery ; i++) 
    {
        // get the string name of a particular image file 
        var name = "w" + String(i) + ".jpg";
        //get an array of this file 
        var singleImageArray = new Array(name);
        // concatenate this to the main array
        imageArray = imageArray.concat(singleImageArray);
    }
    
    // create a function to get a random image complete path from an array of the images 
    function getRandomImageFromArray(array)
    {
        // first get a random number
        var randomNumber = Math.round(Math.random() * array.length);
        // get a random image file name with this random number as an index
        var randomImage = null;
        if(randomNumber !== 0 || randomNumber !== 5 || randomNumber !== 8)
        {
             randomImage = array[randomNumber];
        }
        else
        {
            randomImage = array[1];
        }
        // add the path to the directory to get URL of the image
        var path = "../../images/backgrounds/" + randomImage; 
        return path;
    }
    
    
    function changeBackground()
    {
        var randomBackground = getRandomImageFromArray(imageArray);
        var container = document.getElementsByClassName("content")[0];
        container.style = "background-image: url('" + randomBackground +"')";
    }

    setInterval(changeBackground,100000);
    
    /* Action of the buttons in the Navigation bar 
     * 
     */
    document.getElementById("aboutbutton").onclick = function()
    {
        var path = "aboutpage.html";
        browser.loadPage(path)
    };
    
    document.getElementById("contactbutton").onclick = function()
    {
        var path = "contactpage.html";
        browser.loadPage(path);
    };
    document.getElementById("registerbutton").onclick = function()
    {
        var path = "managementregistrationpage.html";
        // call the java method to load the page in a new tab
        browser.loadPage(path);
    };
    
    document.getElementById("managementbutton").onclick = function()
    {
        var path = "managementloginpage.html";
        browser.loadPage(path);
    };
    
    document.getElementById("teacherbutton").onclick = function()
    {
        var path = "staffloginpage.html";
        browser.loadPage(path);
    }
    
    document.getElementById("studentsbutton").onclick = function()
    {
        var path = "studentloginpage.html";
        browser.loadPage(path);
    };