var ncTable;
var requestSent = false;
var currentRowId;
$(document).ready(function() {
    ncTable = $("#taskTable").DataTable( {
        "ajax": "displayData",
        "rowId": "id",
        "bFilter": false,
        "bInfo": false,
        "bLengthChange": true,
        "bPaginate": false,
        "bSort": false,
        "columns": [
            {
              "data": null,
               defaultContent: ''
            },
            { "data": "taskDescr" },
            { "data": "dueDate" },
            { "data": "timeDue" },
            {
            	   "data": null,
                className: "center",
                defaultContent: '<center><button id="btnDeleteTask" onClick=deleteTask($(this).closest(\'tr\').index()) style="border: none; background: none;"><img src="images/delete.png"></button></center>'
            },
            {
               "data": null,
                className: "center",
                defaultContent: '<a href="#openEdit" id="btnEditTask" onClick=editTask($(this).closest(\'tr\').index())><img src="images/edit.png"></a>'
            },
            {
               "data": null,
                className: "center",
                defaultContent: '<a href="#openDetails" id="btnViewDetails" onClick=viewDetails($(this).closest(\'tr\').index())><img src="images/details.png"></a>'
            }
        ],
        columnDefs: [ 
        	{
            orderable: false,
            className: 'select-checkbox',
            targets:   0
        } ],
        select: {
            style:    'multi',
            selector: 'td:first-child'
        },
        order: [[ 1, 'asc' ]]
        } );
} );

$(function() {
    $('#formAddTask').on('submit', function(event) {
        event.preventDefault();
        var txbAddDes = document.getElementById("txbAddTaskDes").value;
        var txbAddDet = document.getElementById("txtAreaAddDetails").value;
        var txbDueD = document.getElementById("txbDueDate").value;
        var ddHoursElement = document.getElementById("addHour");
        var ddHoursVal = ddHoursElement.options[ddHoursElement.selectedIndex].value;
        var ddMinutesElement = document.getElementById("addMinute");
        var ddMinutesVal = ddMinutesElement.options[ddMinutesElement.selectedIndex].value;
        var ddAmPmElement = document.getElementById("addAmPm");
        var ddAmPmVal = ddAmPmElement.options[ddAmPmElement.selectedIndex].value;
      	  $.ajax({
            url: 'displayData',
            type: 'post',
            method: 'post',
            data: { 
                'type': 'btnAddTask',
                'txbAddTaskDes': txbAddDes,
                'txtAreaAddDetails': txbAddDet,
                'txbDueDate': txbDueD,
                'addHour': ddHoursVal,
                'addMinute': ddMinutesVal,
                'addAmPm': ddAmPmVal
            },
            dataType: 'text',
            success: function(data) {
          	    console.log(data);
          	    var jsonObj = JSON.parse(data);
          	    console.log(jsonObj.validDate);
          	    if (jsonObj.validDate == "false") {
            		    alert('Invalid date. Please enter in mm/dd/yyyy format.');
            	    }
          	    else if (jsonObj.emptyTask == "false") {
          	    		alert('Please enter a task description.');
          	    }
          	    else {
              	    var newRow = ncTable.rows.add([
              	    	{
              	    		'taskDescr': jsonObj.taskDescr,
              	    		'dueDate': jsonObj.dueDate,
              	    		'timeDue': jsonObj.timeDue
              	    	}
              	   ]).draw();
              	   var temp = document.getElementById("taskTable").tBodies[0].rows.length-1;
              	   var lastRow = document.getElementById("taskTable").tBodies[0].rows[temp];
              	   lastRow.setAttribute('id', jsonObj.id);
              	   document.getElementById("formAddTask").reset();
          	    }
        	    }
        });
});
});

function editTask(rowId) {
	currentRowId = rowId;
    var txbEditTaskDes = document.getElementById('txbEditTaskDes');
	var txbEditDueDate = document.getElementById('txbEditDueDate');
	var ddEditHoursElement = document.getElementById("editHour");
	var ddEditHoursVal = ddEditHoursElement.options[ddEditHoursElement.selectedIndex].value;
	var ddEditMinutesElement = document.getElementById("editMinute");
	var ddEditMinutesVal = ddEditMinutesElement.options[ddEditMinutesElement.selectedIndex].value;
	var ddEditAmPmElement = document.getElementById("editAmPm");
	var ddEditAmPmVal = ddEditAmPmElement.options[ddEditAmPmElement.selectedIndex].value;
	var ncCells = taskTable.rows.item(currentRowId+1).cells;
	txbEditTaskDes.value = ncCells[1].innerHTML.trim();
	txbEditDueDate.value = ncCells[2].innerHTML.trim();
	console.log("editTask2: " + currentRowId);
	// Populate details textbox
	$.ajax({
	    type: "post",
	    method: "post",
	    url:"displayData",
	    dataType: "text",
	    data: {
	      	'type': 'btnViewDetails',
	    		'rowId': currentRowId
	    },
	    success: function(data) {
	        var jsonObj = JSON.parse(data);
	        //alert(jsonObj.details);
	    	    $("#txtAreaEditDetails").val(jsonObj.details);
	    }
	});
	var timeArr = ncCells[3].innerHTML.trim().split(":");
	var hour = timeArr[0];
	var tempMin = timeArr[1].split(" ");
	var min = tempMin[0];
	var amPm = tempMin[1];
	
	ddEditHoursElement.value = hour;
	ddEditMinutesElement.value = min;
	ddEditAmPmElement.value = amPm;
	
	$('#formEditTask').on('submit', function(event) {
		event.preventDefault();
	    var txbEditDes = document.getElementById("txbEditTaskDes").value;
	    var txbEditDet = document.getElementById("txtAreaEditDetails").value;
	    var txbEditD = document.getElementById("txbEditDueDate").value;
	    var ddEditHoursElement = document.getElementById("editHour");
	    var ddEditHoursVal = ddEditHoursElement.options[ddEditHoursElement.selectedIndex].value;
	    var ddEditMinutesElement = document.getElementById("editMinute");
	    var ddEditMinutesVal = ddEditMinutesElement.options[ddEditMinutesElement.selectedIndex].value;
	    var ddEditAmPmElement = document.getElementById("editAmPm");
	    var ddEditAmPmVal = ddEditAmPmElement.options[ddEditAmPmElement.selectedIndex].value;
	    
	    if (!requestSent) {
	    	  requestSent = true;
	    	  var thisRow = document.getElementById("taskTable").tBodies[0].rows[currentRowId];
	  	  $.ajax({
	        url: 'displayData',
	        type: 'post',
	        method: 'post',
	        data: { 
	        		'rowId': thisRow.getAttribute('id'),
	            'type': 'btnSubmitEditTask',
	            'txbEditTaskDes': txbEditDes,
	            'txtAreaEditDetails': txbEditDet,
	            'txbEditDueDate': txbEditD,
	            'editHour': ddEditHoursVal,
	            'editMinute': ddEditMinutesVal,
	            'editAmPm': ddEditAmPmVal
	        },
	        dataType: 'text',
	        success: function(data) {
	        	    requestSent = false;
	      	    //console.log(data);
	      	    var jsonObj = JSON.parse(data);
	      	    if (jsonObj.validDate == "false") {
	        		    alert('Invalid date. Please enter in mm/dd/yyyy format.');
	        	    }
	      	    else if (jsonObj.emptyTask == "false") {
	      	    		alert('Please enter a task description.');
	      	    }
	      	    else if (jsonObj.successEdit == "true") {
	      	    	    var taskCells = taskTable.rows.item(currentRowId+1).cells;
	      	    	    taskCells[1].innerHTML = txbEditDes;
	      	    	  	taskCells[2].innerHTML = txbEditD;
	      	    		var strTime = ddEditHoursVal + ":" + ddEditMinutesVal + " " + ddEditAmPmVal;
	      	    		taskCells[3].innerHTML = strTime;
	      	    		$('#taskTable').DataTable().draw();
	      	    }
	      	    else if (jsonObj.successEdit == "false") {
	      	    		alert("Edit unsucessful. Data not found.");
	      	    }
    	    }
	  	});
	    }
});
}
	
$(function() {
	$('#formDeleteAllTask').on('submit', function(event) {
        event.preventDefault();
      	  $.ajax({
            url: 'displayData',
            type: 'post',
            method: 'post',
            data: {
            	'type': 'btnDeleteAll'
            },
            dataType: 'text',
            success: function(data) {
            		var jsonObj = JSON.parse(data);
            		if (jsonObj.deleteAll == "success") {
            			$('#taskTable').DataTable().clear().draw();
            			//alert("All tasks deleted succesfully.");
            		}
            		else {
            			alert("Please try again.");
            		}
        	    }
        });
});
});
	
function deleteTask(rowId) {
var thisRow = document.getElementById("taskTable").tBodies[0].rows[rowId];
$(thisRow).addClass('selected');
	$.ajax({
     url: 'displayData',
     type: 'post',
     method: 'post',
     data: {
     	'type': 'btnDeleteTask',
     	'rowId': thisRow.getAttribute('id')
     },
     dataType: 'text',
     success: function(data) {
    		var jsonObj = JSON.parse(data);
    		if (jsonObj.deleteTask == "success") {
    			$('#taskTable').DataTable().row(rowId).remove().draw();
    			//alert("Deleted task succesfully.");
    		}
    		else {
    			alert("Please try again.");
    		}
     }    
	});
}

function viewDetails(rowId) {
	// Populate details textbox
	$.ajax({
	    type: "post",
	    method: "post",
	    url:"displayData",
	    dataType: "text",
	    data: {
	      	'type': 'btnViewDetails',
	    		'rowId': rowId
	    },
	    success: function(data) {
	        var jsonObj = JSON.parse(data);
	        //alert(jsonObj.details);
	    	    $("#txtAreaViewDetails").val(jsonObj.details);
	    }
	});
}

$(function() {
	$('#formCompleteTask').on('submit', function(event) {
        	  event.preventDefault();
        	  var checkedList = [];
        	  for (var i = 1; i < ncTable.rows().count()+1; i++) {
        	     var thisRow = taskTable.rows.item(i);
        	     var selected = thisRow.getAttribute('class');
        	     if (selected == "even selected" || selected == "odd selected") {
        	    	 	checkedList.push([thisRow.getAttribute('id'),true]);
        	     }
        	     else {
        	    	 	checkedList.push([thisRow.getAttribute('id'),false]);
        	     }
        	  }
        	  
        	  var jsonStr = "{\"checkedData\": [";
        	  for (var j = 0; j < checkedList.length; j++) {
        		  console.log(checkedList[j]);
        		  jsonStr += "{\"id\": \"" + checkedList[j][0] + "\", \"checked\": \"" + checkedList[j][1] + "\"}"; 
        		  if (j+1 != checkedList.length) {
        			  jsonStr += ",";
        		  }
        	  }
        	  jsonStr += "]}";
        	  console.log(jsonStr);
        	  
	  	  $.ajax({
	        url: 'displayData',
	        type: 'post',
	        method: 'post',
	        data: {
	        	'type': 'btnComplete',
	        	'dataStr': jsonStr,
	        	'viewMode': 'c'
	        },
	        dataType: 'text',
	        success: function(data) {
	        		ncTable.clear().draw();
	        		var jsonObj = JSON.parse(data);
	        		for (var i = 0; i < jsonObj.data.length; i++) {
        		        var newRow = ncTable.rows.add([
              	    	{
              	    		'taskDescr': jsonObj.data[i].taskDescr,
              	    		'dueDate': jsonObj.data[i].dueDate,
              	    		'timeDue': jsonObj.data[i].timeDue
              	    	}
              	    ]).draw();
        		        var temp = document.getElementById("taskTable").tBodies[0].rows.length-1;
                   	var lastRow = document.getElementById("taskTable").tBodies[0].rows[temp];
                   	lastRow.setAttribute('id', jsonObj.data[i].id);
	        		 }
	        	     ncTable.columns.adjust().draw();
	    	    }
        });
});
});