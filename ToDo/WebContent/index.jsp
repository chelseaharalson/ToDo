<%-- 
    Document   : index
    Created on : Sep 19, 2017, 2:02:49 AM
    Author     : chelseametcalf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" />
        <link rel="stylesheet" type="text/css" href="css/select.dataTables.min.css" />
        <link rel="stylesheet" type="text/css" href="css/buttons.css" />
        <link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css' />
	<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css' />
        <script src="scripts/jquery-1.12.4.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="scripts/dataTables.select.min.js" type="text/javascript"></script>
        <link rel="shortcut icon" href="images/icon.ico">
        <script src="scripts/modernizr.custom.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
            var ncTable = $("#notCompletedList").DataTable( {
                "ajax": "displayData",
                "bFilter": false,
                "bInfo": false,
                "bLengthChange": false,
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
                        defaultContent: '<a href=""><img src="images/delete.png"></a>'
                    },
                    {
                       "data": null,
                        className: "center",
                        defaultContent: '<a href="#openEdit"><img src="images/edit.png"></a>'
                    },
                    {
                       "data": null,
                        className: "center",
                        defaultContent: '<a href="#openDetails"><img src="images/details.png"></a>'
                    }
                ],
                columnDefs: [ {
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
        	    
                ncTable.page( 'next' ).draw( 'page' );
        } );
        </script>
        <script type="text/javascript">
	        $(function(){
	            var selectHour = '';
	            for (i=1;i<=12;i++){
	              	selectHour += '<option val=' + i + '>' + i + '</option>';
	            }
	            $('#addHour').html(selectHour);
	        });
        </script>
        <script type="text/javascript">
	        $(function(){
	            var selectMin = '';
	            for (i=0;i<=9;i++){
	              	selectMin += '<option val=' + i + '>' + 0 + i + '</option>';
	            }
	            for (i=10;i<=59;i++){
	             	selectMin += '<option val=' + i + '>' + i + '</option>';
	            }
	            $('#addMinute').html(selectMin);
	        });
        </script>
        <script type="text/javascript">
	        $(function(){
	            var selectHour = '';
	            for (i=1;i<=12;i++){
	              	selectHour += '<option val=' + i + '>' + i + '</option>';
	            }
	            $('#editHour').html(selectHour);
	        });
        </script>
        <script type="text/javascript">
	        $(function(){
	            var selectMin = '';
	            for (i=0;i<=9;i++){
	              	selectMin += '<option val=' + i + '>' + 0 + i + '</option>';
	            }
	            for (i=10;i<=59;i++){
	             	selectMin += '<option val=' + i + '>' + i + '</option>';
	            }
	            $('#editMinute').html(selectMin);
	        });
        </script>
        <script type="text/javascript">
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
                        	    //$("#openNewTaskForm").hide();
                        	    alert(data);
                        	  	$('#ncTable').DataTable().draw();
                      	  }
                      });
              });
              });
          </script>
        <title>To Do List</title>
    </head>
    <body>
        <div class="footer">
            <a href=""><span>To Do List</span></a>
            <a href=""><span>Chelsea Metcalf</span></a>
        </div>
        <div class="container">
            <div class="header">
		<h1>To Do List<span>to keep you organized!</span></h1>
	    </div>
        </div>
        <div class="tl_container">
        <table id="tblButtons" cellspacing="0" width="100%">
            <tr>
              <td width="40%"><a href="#openNewTaskForm"><button class="btn btn-1 btn-1a icon-newitem"><span>New Item</span></button></a></td>
              <td width="40%"><button class="btn btn-1 btn-1a icon-complete"><span>Complete All</span></button></td>
              <td width="40%"><button class="btn btn-1 btn-1a icon-remove"><span>Delete All</span></button></td>
              <td width="40%"><button class="btn btn-1 btn-1a icon-show"><span>Show All</span></button></td>
            </tr>
        </table>
        <table id="notCompletedList" class="display notCompletedList" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th><a href="#" class="completeButton">Complete</a></th>
                <th style="width: 68%;">Task</th>
                <th>Due</th>
                <th>Time</th>
                <th>Delete</th>
                <th>Edit</th>
                <th>Details</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
        </div>
        
    <div class="lightbox" id="openNewTaskForm">
	<div class="box">
	    <a class="close" href="#">X</a>
	    <p class="title">Add Task</p>
	    <div class="content">
	    <form method="post" action="DataTableServlet" id="formAddTask" onsubmit="return validateSaveAliasForm()" >
	    <table width="100%">
                <tr>
                   <td><h4>Task: </h4></td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                   <td> 
                       <input value="" type="text" id="txbAddTaskDes" name="txbAddTaskDes" style="width: 400px;" />  
                   </td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><h4>Due Date: </h4></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                        <input value="" type="text" id="txbDueDate" name="txbDueDate" maxlength="10" style="width: 140px;" placeholder='mm/dd/yyyy' />
                    </td>
                 </tr>
                <tr>
                  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                   <td><h4>Details: </h4></td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                   <td> 
                       <textarea rows='3' data-min-rows='3' id="txtAreaAddDetails" name="textAreaAddDetails"></textarea>  
                   </td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><h4>Due Time: </h4></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                        <!-- <input value="" type="text" id="txbTime" name="txbTime" maxlength="5" style="width: 140px;" placeholder='hh:mm'/> -->
                        <select id='addHour' name='addHour'></select> :
                        <select id='addMinute' name='addMinute'></select>
                        <select id='addAmPm' name='addAmPm'>
                        		<option value='AM' >AM</option>
  							<option value='PM'>PM</option>
                        </select>
                    </td>
                 </tr>
            </table>
            <center>
            	<button type="submit" class="btn btn-1 btn-1a icon-newitem" name="btnAddTask" onclick="" >
            		<span>Submit</span>
            	</button>
            </center>
            </form>
            </div>
          </div>
    </div>
        
    <div class="lightbox" id="openDetails">
	<div class="box">
	    <a class="close" href="#">X</a>
	    <p class="title">Details</p>
	    <div class="content">
	    <form method="post" action="SaveDetails" onsubmit="return validateSaveAliasForm()" >
	    <table width="100%">
                <tr>
                   <td><h4>Details: </h4></td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                   <td> 
                       <textarea rows='3' data-min-rows='3'></textarea>  
                   </td>
                   <td>
                       <button class="btn btn-1 btn-1a icon-remove"><span>Delete</span></button>
                   </td>
                 </tr>
            </table>
            <center>
            	<button type="submit" class="btn btn-1 btn-1a icon-newitem" onclick="" >
            		<span>Submit</span>
            	</button>
            </center>
            </form>
            </div>
          </div>
    </div>
        
    <div class="lightbox" id="openEdit">
	<div class="box">
	    <a class="close" href="#">X</a>
	    <p class="title">Edit Task</p>
	    <div class="content">
	    <form method="post" action="EditTask" onsubmit="return validateSaveAliasForm()" >
	    <table width="100%">
                <tr>
                   <td><h4>Task: </h4></td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                   <td> 
                       <input value="" type="text" id="txbEditTask" name="txbEditTask" style="width: 400px;" />  
                   </td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><h4>Due Date: </h4></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                        <input value="" type="text" id="txbDueDate" name="txbDueDate" maxlength="10" style="width: 140px;" placeholder='mm/dd/yyyy' />
                    </td>
                 </tr>
                <tr>
                  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                   <td><h4>Details: </h4></td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                   <td> 
                       <textarea rows='3' data-min-rows='3'></textarea>  
                   </td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><h4>Due Time: </h4></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                        <select id="editHour" name="editHour"></select> :
                        <select id='editMinute' name="editMinute"></select>
                        <select id='editAmPm' name="editAmPm">
                        		<option value="AM">AM</option>
  							<option value="PM">PM</option>
                        </select>
                    </td>
                 </tr>
            </table>
            <center>
            	<button type="submit" class="btn btn-1 btn-1a icon-newitem" onclick="" >
            		<span>Submit</span>
            	</button>
            </center>
            </form>
            </div>
          </div>
    </div>
        
    </body>
</html>
