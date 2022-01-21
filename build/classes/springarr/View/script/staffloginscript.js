 // get the working lements
    var passwordField = document.getElementById('passwordField');
    var usernameField = document.getElementById('loginUsername');
    var loginButton = document.getElementById('loginButton');
   

    passwordField.onmouseenter = function()
    {
        passwordField.focus();
    };

    usernameField.onmouseenter = function()
    {
        usernameField.focus();
    };
    
    loginButton.onmouseenter = function()
    {
        loginButton.focus();
    };
    
    loginButton.onmouseout = function()
    {
        usernameField.focus();
    };


// function for the login button 
   loginButton.onclick = function ()
    {
        var messageSpan = document.getElementById('messageLine');
        if(passwordField.value.length === 0 && usernameField.value.length > 0)
        {
            messageSpan.innerHTML = "Password field cannot be empty!";
            messageSpan.style = "color: red; font-size: 12pt; font-style: italic;";
            passwordField.style = "border-color: red; border-width: 0.1mm;";
            usernameField.style = "border-color: transparent; border-width: 0mm;";
            passwordField.focus();
        }

        else if(usernameField.value.length === 0 && passwordField.value.length > 0)
        {
             messageSpan.innerHTML = "Provide your username";
             messageSpan.style = "color: red; font-size: 12pt; font-style: italic;";
             usernameField.style = "border-color: red; border-width: 0.1mm;";
             passwordField.style = "border-color: transparent; border-width: 0mm;";
             usernameField.focus();
        }

        else if (passwordField.value.length === 0 && usernameField.value.length === 0)
        {
            messageSpan.innerHTML = "Provide both your username and password";
            messageSpan.style = "color: red; font-size: 12pt; font-style: italic;";
            usernameField.style = "border-color: red; border-width: 0.1mm;";
            passwordField.style = "border-color: red; border-width: 0.1mm;";
            usernameField.focus();
        }

        else
        {
            // get the values of the input
            var usernameValue = usernameField.value.trim();
            var passwordValue = passwordField.value;
            // form the entry object
            var userObject = {username:usernameValue, password:passwordValue};  // to be later used
            // get the user that signed in either "admin, teacher or student"
            var user = getUser();
            // perform action based on the user
            switch(user)
            {
                case "admin": browser.loadAdmin(); break;
                case "teacher": browser.loadStaff(); break;
                case "student": browser.loadStudent(); break;
            }
        }
    };

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
        if(randomNumber !== 0 || randomNumber  !== 5 || randomNumber !== 8)
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
        document.body.style = "background-image: url('" + randomBackground +"')";
    }
    
    setInterval(changeBackground,20000);
    
    function getUser()
    {
        // get all the header elements in the document 
        var headerArray = document.getElementsByTagName('th');
        // The first header is the most important one 
        var header = headerArray[0];
        // get the id of the header
        var headerId = header.id;
        // check the type of header and return the user
        switch(headerId)
        {
            case "admin": return "admin"; break;
            case "teacher": return "teacher"; break;
            case "student": return "student"; break;
        }
    }