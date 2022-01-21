/*

*/

/*
	Write a function to create the entry details if the user click yes for the question:
	Do you want Mysql database system?
*/


    var mysqlYesbutton = document.getElementById('mysqlYes');
    
    var mysqlNobutton = document.getElementById('mysqlNo');

    var msaccessYesbutton = document.getElementById('msaccessYes');

    var msaccessNoButton = document.getElementById('msaccessNo'); 

	// get the mysql table 
	var table = document.getElementById('responseTable');
 	mysqlYesbutton.onclick =  function ()
		{
			table.style = "display: inherit;";
		}			

 	mysqlNobutton.onclick = function()
	 {
	 	table.style = "display: none;";
	 }


// get the ms acess table 
	var msaccessTable = document.getElementById('accessresponse');
	msaccessYesbutton.onclick = function()
	{
		msaccessTable.style = "display: inherit;";
	}

	msaccessNoButton.onclick = function()
	{
		msaccessTable.style = "display: none";
	}


/* Function to remove all childs */
    function removeAll(parent)
    {
        var children = parent.childNodes;
        for(var i = 0; i < children.length; i++)
        {
            var child = children[i];
            parent.removeChild(child);
        }
    }
// get the design table 
	var designTable = document.getElementById('designTable');
	var designYesbutton = document.getElementById('yesButton');
	var designNoButton = document.getElementById('noButton');

	designYesbutton.onclick = function()
	{
		designTable.style = "display: inherit;";
	}


	/*
		Function to set attributes of created element 
	*/

	function setAttributes(element, object)
	{
		for(var i = 0; i < object.length; i++)
		{
			// get the first array
			var array = object[i];
			element.setAttribute(array[0],array[1]);
		}
	}

	/* Function to append all child
	*/
	function appendAll(row, childArray)
	{
		for(var i = 0; i <childArray.length; i++)
		{
			row.appendChild(childArray[i]);
		}
	}

	//get the add design button
	var designbutton = document.getElementById('adddesignbutton');
	designbutton.onclick = function()
	{
		// create a new row 
		var file = document.createElement('input');
		setAttributes(file,[["type","file"],["style","width: 50mm; font-size: 8pt;"]]);

		var fileColumn = document.createElement('td');
		fileColumn.appendChild(file);

		var img = document.createElement('img');
		setAttributes(img, [["width","60"],["heigth", "60"],["src","b2.jpg"],["style","display: none"]]);
		var imageColumn = document.createElement('td');
		imageColumn.appendChild(img);

		var removeButton = document.createElement('input');
		setAttributes(removeButton, 
			[["type","button"],["value","remove"],["class","remove"],
			["style","width: 20mm; font-size: 8pt; background-color: transparent; color: red; font-weight: bold; border-color: transparent; border-width: 0mm; cursor: pointer;"]]);
		var removeColumn = document.createElement('td');
		removeColumn.appendChild(removeButton);

		// create a new row 
		var row = document.createElement('tr');
		appendAll(row,[fileColumn, imageColumn, removeColumn]);

		// append to the original table
		var table = document.getElementById('designTable');
		table.appendChild(row);

		file.onclick = function()
		{
                    var u = javascriptHandler.print();
			// call the java function to get the file base64 string
                     var imagePath = javascriptHandler.getImageStream();
			// set the source attribute to the stream 
			img.src = imagePath;
			img.style = "display: inherit";
                        file.disabled = true;
		};

		removeButton.onclick = function()
		{
			table.removeChild(row);
		};

	};
        
/**
 * 
 * @param {type} array
 * @param {type} element
 * @returns {Array}
 */
function append(array, element)
{
    // define the new array 
    var newArray = new Array(array.length + 1);
    // copy all the elements in the array to the new array 
    for (var i = 0; i < array.length; i++)
    {
        newArray[i] = array[i];
    }
    // add the last element to the array 
    newArray[array.length] = element;
    // return the new array 
    return newArray;
}


/** This method get the string representation of any array to be sent to the database server */
/**
 * 
 * @param {type} array
 * @returns {String}
 */
function toString(array)
{
    var string = "";
    for(var i = 0; i < array.length - 1; i++)
    {
        string+= array[i] + "#";
    }
    //add the final
    string += array[array.length - 1];
    // return the string
    return string;
}

/**
 * 
 * @returns {Array}
 */
function getSchoolDataArray()
{
    var schoolDataArray = new Array();
    for(var i = 1; i <= 12; i++)
    {
        var id = "s" + i;
        schoolDataArray = append(schoolDataArray, document.getElementById(id).value);
    }
    return schoolDataArray;
}

/**
 * This method returns the management personal data array
 * @returns {Array}
 */
function getManagementPersonalDataArray()
{
    var array = new Array();
    for(var i = 1; i <=5; i++)
    {
        var id = "mp" + i;
        array = append(array, document.getElementById(id).value);
    }
    return array;
}

/**
 * 
 * @returns {Array}
 */
function getManagementContactDataArray()
{
    var array = new Array();
    for(var i = 1; i <= 8; i++)
    {
        var id = "mc" + i;
        array = append(array, document.getElementById(id).value);
    }
    return array;
}

/**
 * 
 * @returns {Array}
 */
function getManagementLoginDataArray()
{
    var array = new Array();
    for(var i = 1; i <= 3; i++)
    {
        var id = "ml" + i;
        array = append(array, document.getElementById(id).value);
    }
    return array;
}

/**
 * 
 * @type Element
 */
    function handleInput(id)
    {
        var element = document.getElementById(id);
            var value = element.value;
            if((Number(value)+ "0").substring(0,3)=== "NaN")
            {
                element.style = "border-color: red; border-width: 0.6mm";
                element.focus();
                element.placeholder = "Enter number, not text!";
                element.value = "";
            }
            else
            {
                element.style = "border-color: black;";
                element.value = value;
            }
    }

    document.getElementById("s4").onblur = function(){handleInput("s4");};
    document.getElementById("s6").onblur = function(){handleInput("s6");};
    document.getElementById("s10").onblur = function(){handleInput("s10");};
    document.getElementById("s11").onblur = function(){handleInput("s11");};
    document.getElementById("s12").onblur = function(){handleInput("s12");};


var submit = document.getElementById("submitbutton");
submit.onclick = function()
{
    var inputs = document.getElementsByTagName("input");
    for(var input of inputs)
    {
        
    }
};