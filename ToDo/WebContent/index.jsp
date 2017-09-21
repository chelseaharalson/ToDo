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
        <script src="scripts/moment.js"></script>
        <script src="scripts/combodate.js"></script>
        <script type="text/javascript">

        $(document).ready(function() {
        		/*var json;
        	
        	    $.get("displayData",function(responseText){
        	    		console.log(responseText);
        	    		json = JSON.parse(responseText);
        	    		console.log(json);
        	    });*/
			var json;
            $("#notCompletedList").DataTable( {
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
        } );

        </script>
        <script type="text/javascript">
            $(function(){
                $('#time12').combodate();  
            });
        </script>
        <script type="text/javascript">
            $(function(){
                $('#dueDate').combodate({
                    minYear: 2017,
                    maxYear: 2021,
                    minuteStep: 10,
                    yearDescending: false
                });  
            });
        </script>
        <script type="text/javascript">
            $(function(){
                $('#timeEdit12').combodate();  
            });
        </script>
        <script type="text/javascript">
            $(function(){
                $('#dueEditDate').combodate({
                    minYear: 2017,
                    maxYear: 2021,
                    minuteStep: 10,
                    yearDescending: false
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
	    <form method="post" action="AddTask" onsubmit="return validateSaveAliasForm()" >
	    <table width="100%">
                <tr>
                   <td><h4>Task: </h4></td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                   <td> 
                       <input value="" type="text" id="txbAddTask" name="txbAddTask" style="width: 400px;" />  
                   </td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><h4>Due Date: </h4></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                        <!-- <input value="" type="text" id="txbDueDate" name="txbDueDate" maxlength="10" style="width: 140px;" placeholder='mm/dd/yyyy' /> -->
                        <input id="dueDate" value="01-01-2017" data-format="DD-MM-YYYY" data-template="D MMM YYYY">
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
                        <!-- <input value="" type="text" id="txbTime" name="txbTime" maxlength="5" style="width: 140px;" placeholder='hh:mm'/> -->
                        <input type="text" id="time12" data-format="h:mm a" data-template="hh : mm a" name="datetime" value="12:00 am">
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
                        <input id="dueEditDate" value="01-01-2017" data-format="DD-MM-YYYY" data-template="D MMM YYYY">
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
                        <input type="text" id="timeEdit12" data-format="h:mm a" data-template="hh : mm a" name="datetime" value="12:00 am">
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
