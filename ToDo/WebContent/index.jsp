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
        <script src="scripts/ToDoListLogic.js" type="text/javascript"></script>
        <link rel="shortcut icon" href="images/icon.ico">
        <script type="text/javascript">
	        $(function(){
	            var selectHour = '';
	            for (i=1;i<=12;i++){
	              	selectHour += '<option val=' + i + '>' + i + '</option>';
	            }
	            $('#addHour').html(selectHour);
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
	            $('#addMinute').html(selectMin);
	            $('#editMinute').html(selectMin);
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
              <td width="40%">
              	  <form method="post" action="DataTableServlet" id="formCompleteAllTask">
              		<button class="btn btn-1 btn-1a icon-complete"><span>Complete All</span></button>
              	  </form>
              </td>
              <td width="40%">
              	<form method="post" action="DataTableServlet" id="formDeleteAllTask">
              		<button class="btn btn-1 btn-1a icon-remove" id="btnDeleteAll"><span>Delete All</span></button>
              	</form>
              </td>
              <td width="40%">
              	  <form method="post" action="DataTableServlet" id="formShowAllTask">
              		<button class="btn btn-1 btn-1a icon-show"><span>Show All</span></button>
              	  </form>
              </td>
            </tr>
        </table>
        <table id="notCompletedList" class="display notCompletedList" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th><a href="#" class="completeButton">Complete</a></th>
                <th style="width: 80%;">Task</th>
                <th style="width: 1%;">Due</th>
                <th style="width: 9%;">Time</th>
                <th style="width: 1%;">Delete</th>
                <th style="width: 1%;">Edit</th>
                <th style="width: 1%;">Details</th>
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
	    <form method="post" action="DataTableServlet" id="formAddTask">
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
                        		<option value='AM'>AM</option>
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
	    <p class="title">View Details</p>
	    <div class="content">
	    <table width="100%">
                <tr>
                   <td><h4>Details: </h4></td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                   <td> 
                       <textarea id="txtAreaViewDetails" rows='3' data-min-rows='3' style="width: 700px; height: 100px;"></textarea>  
                   </td>
                 </tr>
            </table>
            </div>
          </div>
    </div>
        
    <div class="lightbox" id="openEdit">
	<div class="box">
	    <a class="close" href="#">X</a>
	    <p class="title">Edit Task</p>
	    <div class="content">
	    <form method="post" action="formEditTask" onsubmit="" >
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
                        <input value="" type="text" id="txbEditDueDate" name="txbEditDueDate" maxlength="10" style="width: 140px;" placeholder='mm/dd/yyyy' />
                    </td>
                 </tr>
                <tr>
                  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                   <td><h4>Details: </h4></td>
                   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                   <td> 
                       <textarea rows='3' data-min-rows='3' id="txtAreaEditDetails"></textarea>  
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
