<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="icon" type="image/png" href="resources/img/favicon.ico">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>${title}</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="resources/css/dashboard/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="resources/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="resources/css/light-bootstrap-dashboard.css" rel="stylesheet"/>
    
    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="resources/css/pe-icon-7-stroke.css" rel="stylesheet" />
    <!-- Angular Related Stylesheets -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
    <script type="text/javascript" src="resources/js/angular/onGoingQuiz.js"></script>
        
</head>
<body>

<div class="wrapper" data-ng-app="pupilArenaApplication">
    <div class="sidebar" data-color="orange" data-image="resources/img/sidebar-5.jpg" data-ng-controller="sidebarController" data-ng-init="loadDetails()">

    <!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->


    	<div class="sidebar-wrapper">
            <div class="logo">
                <a class="simple-text">
                    {{fullName}}
                </a>
            </div>

            <ul class="nav">
                <li data-ng-show="isLeader">
                    <a href="create_team">
                        <i class="pe-7s-graph"></i>
                        <p>Create Team</p>
                    </a>
                </li>
                <li data-ng-show="isMember">
                    <a href="join_team">
                        <i class="pe-7s-graph"></i>
                        <p>Join Team</p>
                    </a>
                </li>
                <li>
                    <a href="quizes">
                        <i class="pe-7s-note2"></i>
                        <p>Quizzes</p>
                    </a>
                </li>
                <li class="active">
                    <a href="home">
                        <i class="pe-7s-user"></i>
                        <p>User Profile</p>
                    </a>
                </li>
                
                <li>
                    <a href="notifications.html">
                        <i class="pe-7s-bell"></i>
                        <p>Notifications</p>
                    </a>
                </li>
				<li class="active-pro">
                    <a href="" data-ng-click="logout()">
                        <i class="pe-7s-power"></i>
                        <p>Logout</p>
                    </a>
                </li>
            </ul>
    	</div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
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


        <div class="content" data-ng-controller="quizController" data-ng-init="loadQuizQuestion()">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Question {{questionNumber}}</h4>
                            </div>
                            <div class="content">
                                <form name="myForm" novalidate>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>{{currentQuestion.questionText}}</label>
                                                <!-- input type="text" class="form-control" required="required" name="firstname" data-ng-model="firstname" placeholder="First Name.." data-ng-class="myForm.firstname.$touched && myForm.firstname.$invalid ? 'alert-danger' : ''"-->
                                            </div>
                                        </div>
                                        
                                    </div>
                                    <!--  check if there is image associated to question -->
                                    <div class="row" data-ng-show="currentQuestion.imgUrl!=null">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <img alt="" src="resources/img/quiz/{{currentQuestion.imgUrl}}">
                                            </div>
                                        </div>
                                        
                                    </div>

									
									<div class="input-group form-group-no-border input-lg">
		                                <div>
										    <input type="radio" name="answer" data-ng-model="answer" id="radio1" value="A">
										    <label for="radio1">
										        {{currentQuestion.optionA}}
										    </label>
										</div>
									</div>
									<div class="input-group form-group-no-border input-lg">
										<div>
										    <input type="radio" name="answer" data-ng-model="answer" id="radio2" value="B">
										    <label for="radio2">
										        {{currentQuestion.optionB}}
										    </label>
										</div>
									</div>
									<div class="input-group form-group-no-border input-lg">
										<div>
										    <input type="radio" name="answer" data-ng-model="answer" id="radio3" value="C">
										    <label for="radio3">
										        {{currentQuestion.optionC}}
										    </label>
										</div>
									</div>
									<div class="input-group form-group-no-border input-lg">
										<div>
										    <input type="radio" name="answer" data-ng-model="answer" id="radio4" value="D">
										    <label for="radio4">
										        {{currentQuestion.optionD}}
										    </label>
										</div>
									</div>
									<div class="input-group form-group-no-border input-lg">
										<div>
										    <input type="radio" name="answer" data-ng-model="answer" id="radio5" value="E">
										    <label for="radio5">
										        {{currentQuestion.optionE}}
										    </label>
										</div>
										
		                            </div>
											

                                    <!-- div class="row">
                                        
                                        <div class="col-md-6">
                                        	<div class="form-group">
                                                <label>Gender</label>
                                        	
                                            
											    <input type="radio" name="sex" data-ng-model="sex" id="radio1" value="Male">
											    <label for="radio1">
											        Male
											    </label>
											
											    <input type="radio"  name="sex"  data-ng-model="sex" id="radio2" value="Female">
											    <label for="radio2">
											        Female
											    </label>
											
											</div>
											
                                        </div>
                                        <div class="col-md-6">
                                        	
                                            <!-- div class="checkbox checkbox-primary" data-ng-class="{'checked' : role =='Leader'}">
                                            	
											    <input id="checkbox1" type="checkbox" data-ng-model="role" ng-true-value="'Leader'" ng-false-value="'Member'">
											    <label for="checkbox1">
											        I want to be Team Leader!!
											    </label>
											<!--/div>
											
                                        </div>
                                    </div-->
                                    

                                    <button type="submit" data-ng-hide="isLastQuestion" data-ng-click="submitAnswer(currentQuestion.questionId)" class="btn btn-info btn-fill pull-left">Submit</button>
                                    <button type="submit" data-ng-show="isLastQuestion" data-ng-click="submitLastAnswer(currentQuestion.questionId)" class="btn btn-info btn-fill pull-right">Submit and Finish</button>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- div class="col-md-4">
                        <div class="card card-user">
                            <div class="image">
                                <img src="https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&fm=jpg&h=300&q=75&w=400" alt="..."/>
                            </div>
                            <div class="content">
                                <div class="author">
                                     <a href="#">
                                    <img class="avatar border-gray" src="resources/img/faces/face-3.jpg" alt="..."/>

                                      <h4 class="title">Mike Andrew<br />
                                         <small>michael24</small>
                                      </h4>
                                    </a>
                                </div>
                                <p class="description text-center"> "Lamborghini Mercy <br>
                                                    Your chick she so thirsty <br>
                                                    I'm in that two seat Lambo"
                                </p>
                            </div>
                            <hr>
                            <div class="text-center">
                                <button href="#" class="btn btn-simple"><i class="fa fa-facebook-square"></i></button>
                                <button href="#" class="btn btn-simple"><i class="fa fa-twitter"></i></button>
                                <button href="#" class="btn btn-simple"><i class="fa fa-google-plus-square"></i></button>

                            </div>
                        </div>
                    </div> -->

                </div>
            </div>
        </div>


        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="#">
                                PR9
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                About Us
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Blog
                            </a>
                        </li>
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
                    </script>, Designed by <a href="#" target="_blank">PR9</a>. Coded by <a href="#" target="_blank">PR9</a>.
                </p>
            </div>
        </footer>

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
