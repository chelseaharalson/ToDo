var ncTable;
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

var newRowId;
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
              	  	//$('#ncTable').DataTable().draw();
              	  	//console.log(jsonObj.taskDescr);
              	  	newRowId = jsonObj.id;
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
	//alert(rowId);
    var txbEditTaskDes = document.getElementById('txbEditTaskDes');
	//var txbEditDetails = document.getElementById('txtAreaEditDetails');
	var txbEditDueDate = document.getElementById('txbEditDueDate');
	var ddEditHoursElement = document.getElementById("editHour");
	var ddEditHoursVal = ddEditHoursElement.options[ddEditHoursElement.selectedIndex].value;
	var ddEditMinutesElement = document.getElementById("editMinute");
	var ddEditMinutesVal = ddEditMinutesElement.options[ddEditMinutesElement.selectedIndex].value;
	var ddEditAmPmElement = document.getElementById("editAmPm");
	var ddEditAmPmVal = ddEditAmPmElement.options[ddEditAmPmElement.selectedIndex].value;
	var ncCells = taskTable.rows.item(rowId+1).cells;
	//alert(ncCells[1].innerHTML.trim());
	txbEditTaskDes.value = ncCells[1].innerHTML.trim();
	txbEditDueDate.value = ncCells[2].innerHTML.trim();
	// Populate details textbox
	$.ajax({
	    type: "GET",
	    url:"displayData",
	    dataType: "text",
	    success: function(data) {
	        var jsonObj = JSON.parse(data);
	        //alert(data);
	    	    $("#txtAreaEditDetails").val(data);
	    }
	});
	//alert(ncCells[3].innerHTML.trim());
	var timeArr = ncCells[3].innerHTML.trim().split(":");
	var hour = timeArr[0];
	var tempMin = timeArr[1].split(" ");
	var min = tempMin[0];
	var amPm = tempMin[1];
	
	ddEditHoursElement.value = hour;
	ddEditMinutesElement.value = min;
	ddEditAmPmElement.value = amPm;
	
	var thisRow = document.getElementById("taskTable").tBodies[0].rows[rowId];
	
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
	      	    console.log(data);
	      	    var jsonObj = JSON.parse(data);
	      	    console.log(jsonObj.validDate);
	      	    if (jsonObj.validDate == "false") {
	        		    alert('Invalid date. Please enter in mm/dd/yyyy format.');
	        	    }
	      	    else if (jsonObj.emptyTask == "false") {
	      	    		alert('Please enter a task description.');
	      	    }
	      	    else if (jsonObj.successEdit == "true") {
	      	    		var rowData = document.getElementById("taskTable").tBodies[0].rows[rowId].data();
	      	    		alert(rowData[1]);
	      	    		rowData[1] = txbEditDes;
	      	    		rowData[2] = txbEditD;
	      	    	    var strTime = ddEditHoursVal + ":" + ddEditMinutesVal + " " + ddEditAmPmVal;
	      	    	    rowData[3] = strTime;
	      	    	    $('#taskTable').DataTable().draw();
	          	  	//alert("Edit successful");
	      	    }
	      	    else if (jsonObj.successEdit == "false") {
	      	    		alert("Edit unsucessful. Data not found.");
	      	    }
    	    }
    });
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
        var tableControl = document.getElementById('taskTable');
        var arrayOfValues = [];
         $('#btnComplete').click(function() {
        	 	var checkedValues = $("input:checkbox:checked", "#taskTable").map(function() {
        	        return $(this).val();
        	    }).get();
        	    alert(checkedValues.join(','));
         });
         
      	  $.ajax({
            url: 'displayData',
            type: 'post',
            method: 'post',
            data: {
            	'type': 'btnComplete'
            },
            dataType: 'text',
            success: function(data) {

        	    }
        });
});
});