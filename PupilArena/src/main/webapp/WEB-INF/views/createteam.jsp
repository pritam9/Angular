<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<link rel="icon" type="image/png" href="resources/img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>${title}</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />


<!-- Bootstrap core CSS     -->
<link href="resources/css/dashboard/bootstrap.min.css" rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="resources/css/animate.min.css" rel="stylesheet" />

<!--  Light Bootstrap Table core CSS    -->
<link href="resources/css/light-bootstrap-dashboard.css"
	rel="stylesheet" />

<!--     Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300'
	rel='stylesheet' type='text/css'>
<link href="resources/css/pe-icon-7-stroke.css" rel="stylesheet" />
<!-- Angular Related Stylesheets -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
<script type="text/javascript" src="resources/js/angular/createTeam.js"></script>

</head>
<body>

	<div class="wrapper" data-ng-app="pupilArenaApplication">
		<div class="sidebar" data-color="orange"
			data-image="resources/img/sidebar-5.jpg"
			data-ng-controller="sidebarController" data-ng-init="loadDetails()">

			<!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->


			<div class="sidebar-wrapper">
				<div class="logo">
					<a class="simple-text"> {{fullName}} </a>
				</div>

				<ul class="nav">
					<li data-ng-show="isLeader" class="active"><a
						href="create_team"> <i class="pe-7s-graph"></i>
							<p>Create Team</p>
					</a></li>
					<li data-ng-show="isMember"><a href="join_team"> <i
							class="pe-7s-graph"></i>
							<p>Join Team</p>
					</a></li>
					<li><a href="quizes"> <i class="pe-7s-note2"></i>
							<p>Quizes</p>
					</a></li>
					<li><a href="home"> <i class="pe-7s-user"></i>
							<p>User Profile</p>
					</a></li>

					<!-- li>
                    <a href="typography.html">
                        <i class="pe-7s-news-paper"></i>
                        <p>Typography</p>
                    </a>
                </li>
                <li>
                    <a href="icons.html">
                        <i class="pe-7s-science"></i>
                        <p>Icons</p>
                    </a>
                </li>
                <li>
                    <a href="maps.html">
                        <i class="pe-7s-map-marker"></i>
                        <p>Maps</p>
                    </a>
                </li-->
					<li><a href="notifications.html"> <i class="pe-7s-bell"></i>
							<p>Notifications</p>
					</a></li>
					<li class="active-pro"><a href="" data-ng-click="logout()">
							<i class="pe-7s-power"></i>
							<p>Logout</p>
					</a></li>
				</ul>
			</div>
		</div>

		<div class="main-panel">
			<nav class="navbar navbar-default navbar-fixed">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#navigation-example-2">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">Pupil Arena</a>
					</div>
					<!-- div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-dashboard"></i>
                            </a>
                        </li>
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-globe"></i>
                                    <b class="caret"></b>
                                    <span class="notification">5</span>
                              </a>
                              <ul class="dropdown-menu">
                                <li><a href="#">Notification 1</a></li>
                                <li><a href="#">Notification 2</a></li>
                                <li><a href="#">Notification 3</a></li>
                                <li><a href="#">Notification 4</a></li>
                                <li><a href="#">Another notification</a></li>
                              </ul>
                        </li>
                        <li>
                           <a href="">
                                <i class="fa fa-search"></i>
                            </a>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li>
                           <a href="">
                               Account
                            </a>
                        </li>
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    Dropdown
                                    <b class="caret"></b>
                              </a>
                              <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                              </ul>
                        </li>
                        <li>
                            <a href="#">
                                Log out
                            </a>
                        </li>
                    </ul>
                </div-->
				</div>
			</nav>


			<div class="content" data-ng-controller="createTeamController"
				data-ng-init="loadMyTeamDetails()">
				<div class="container-fluid">
					<!-- div to create team -->
					<div class="row" data-ng-hide="isMember">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Edit Profile</h4>
								</div>
								<div class="content">
									<form name="myForm" novalidate>
										<div class="row">
											<div class="col-md-8">
												<div class="form-group">
													<label>Group Name : </label> <input type="text"
														class="form-control" name="groupName" required="required"
														data-ng-model="groupName" placeholder="Group Awesome4.."
														data-ng-class="myForm.groupName.$touched && myForm.groupName.$invalid ? 'alert-danger' : ''">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label>Max Capacity : </label> <input type="number"
														name="capacity" class="form-control"
														data-ng-model="capacity" placeholder="4"
														data-ng-class="myForm.capacity.$touched && myForm.capacity.$invalid ? 'alert-danger' : ''">
												</div>
											</div>
										</div>
										<button type="submit" data-ng-click="createGroup()"
											class="btn btn-info btn-fill pull-right">Create
											Group</button>
										<div class="clearfix"></div>
									</form>
								</div>
							</div>
						</div>

					</div>
					<!-- End Create Team Form -->

					<!-- Div to view my Group details -->
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Join Teams</h4>
								</div>
								<div class="content">
									<div class="row">
										<div class="col-sm-6 col-md-12" data-ng-repeat="team in teams">
											<div class="thumbnail">

												<div class="panel panel-primary">
													<div class="panel-heading">
														<h3 class="panel-title">{{team.groupId}}
															{{team.groupName}}</h3>
													</div>
													<div class="panel-body">
														<!--p class="panel-title">Capacity:</p>
                                                 <p>{{team.capacity}}</p>
                                                 <p class="panel-title">Members:</p-->
														<div class="content table-responsive table-full-width">
															<table class="table table-hover table-striped">
																<thead class="thead-inverse">
																	<th>First Name</th>
																	<th>Last Name</th>
																	<th>Email</th>
																	<th>School Name</th>
																	<th>Gender</th>
																	<th>GPA</th>
																	<th>Add</th>
																</thead>
																<tbody>
																	<tr data-ng-repeat="member in team.members">
																		<td>{{member.firstname}}</td>
																		<td>{{member.lastname}}</td>
																		<td>{{member.email}}</td>
																		<td>{{member.school_name}}</td>
																		<td>{{member.sex}}</td>
																		<td>{{member.gpa}}</td>

																		<td><i class="pe-7s-trash"
																			data-ng-hide="member.role=='Leader'"
																			data-ng-click="removeMember(member.email)"></i></td>
																	</tr>
																</tbody>
															</table>

														</div>


														<p>
															<a href="#" class="btn btn-warning pull-right"
																data-ng-click="deleteGroup(team.groupId)" role="button">Delete
																Group</a> <a href="#" class="btn btn-info pull-left"
																data-ng-click="deleteGroup(team.groupId)" role="button">Add
																Members</a>
														</p>


													</div>
												</div>

											</div>
										</div>

									</div>
								</div>
							</div>
						</div>

					</div>
					<!-- End div view group details -->

					<!-- Div to view all members to add to the group -->
					<div class="row" data-ng-show="isMember">
						<div class="col-md-12">
							<div class="card">

								<div class="header">
									<h4 class="title">Member List</h4>
									<p class="category">You can add members to group here..</p>
									<div
										class="input-group form-group-no-border input-sm pull-right">
										<span class="input-group-addon"> <i
											class="pe-7s-search"></i>
										</span> <input class="search form-control"
											placeholder="Search with firstname, lastname or email_id..."
											data-ng-model="searchText">
									</div>

								</div>


								<div class="content table-responsive table-full-width">
									<table class="table table-hover table-striped">
										<thead>
											<th>First Name</th>
											<th>Last Name</th>
											<th>Email</th>
											<th>School Name</th>
											<th>Gender</th>
											<th>GPA</th>
											<th>Add</th>
										</thead>
										<tbody>
											<tr data-ng-repeat="member in memberList | filter:searchText">
												<td>{{member.firstname}}</td>
												<td>{{member.lastname}}</td>
												<td>{{member.email}}</td>
												<td>{{member.school_name}}</td>
												<td>{{member.sex}}</td>
												<td>{{member.gpa}}</td>

												<td><i class="pe-7s-add-user"
													data-ng-click="addMember(member.email)"></i></td>
											</tr>
										</tbody>
									</table>

								</div>
							</div>
						</div>

					</div>
					<!-- End div view all members -->

				</div>
			</div>


			<footer class="footer">
				<div class="container-fluid">
					<nav class="pull-left">
						<ul>
							<li><a href="#"> PR9 </a></li>
							<li><a href="#"> About Us </a></li>
							<li><a href="#"> Blog </a></li>
							<!-- li>
                            <a href="https://github.com/creativetimofficial/now-ui-kit/blob/master/LICENSE.md">
                                MIT License
                            </a>
                        </li> -->
						</ul>
					</nav>
					<p class="copyright pull-right">
						&copy;
						<script>
                        document.write(new Date().getFullYear())
                    </script>
						, Designed by <a href="#" target="_blank">PR9</a>. Coded by <a
							href="#" target="_blank">PR9</a>.
					</p>
				</div>
			</footer>

			<!-- footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="#">
                                Home
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Company
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Portfolio
                            </a>
                        </li>
                        <li>
                            <a href="#">
                               Blog
                            </a>
                        </li>
                    </ul>
                </nav>
                <p class="copyright pull-right">
                    &copy; 2016 <a href="http://www.creative-tim.com">Creative Tim</a>, made with love for a better web
                </p>
            </div>
        </footer> -->

		</div>
	</div>


</body>

<!--   Core JS Files   -->
<script src="resources/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="resources/js/core/tether.min.js" type="text/javascript"></script>
<script src="resources/js/core/bootstrap.min.js" type="text/javascript"></script>


<!--  Checkbox, Radio & Switch Plugins -->
<script src="resources/js/bootstrap-checkbox-radio-switch.js"></script>

<!--  Charts Plugin -->
<!-- script src="resources/js/chartist.min.js"></script-->

<!--  Notifications Plugin    -->
<script src="resources/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<!-- script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script-->

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<!--  script src="resources/js/light-bootstrap-dashboard.js"></script-->



</html>
