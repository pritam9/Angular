<!DOCTYPE html>
<html lang="en">

<head>
	<title>${title}</title>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="resources/img/apple-icon.png">
    <link rel="icon" type="image/png" href="resources/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Login Page - Now Ui Kit by Creative Tim</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
    <!-- CSS Files -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" />
    <link href="resources/css/now-ui-kit.css" rel="stylesheet" />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="resources/css/demo.css" rel="stylesheet" />
    <!-- Angular Related Stylesheets -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
    <script type="text/javascript" src="resources/js/angular/signup.js"></script>
        
</head>


<body class="signup-page" data-ng-app="pupilArenaApplication">
    <!-- Navbar -->
    <nav class="navbar navbar-toggleable-md bg-primary fixed-top navbar-transparent " color-on-scroll="500">
        <div class="container">
            <div class="dropdown button-dropdown">
                <a href="#pablo" class="dropdown-toggle" id="navbarDropdown" data-toggle="dropdown">
                    <span class="button-bar"></span>
                    <span class="button-bar"></span>
                    <span class="button-bar"></span>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-header">Dropdown header</a>
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Separated link</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">One more separated link</a>
                </div>
            </div>
            <div class="navbar-translate">
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-bar bar1"></span>
                    <span class="navbar-toggler-bar bar2"></span>
                    <span class="navbar-toggler-bar bar3"></span>
                </button>
                <a class="navbar-brand" href="#" rel="tooltip" title="Designed by PR9. Coded by PR9" data-placement="bottom" target="_blank">
                    Pupil Arena
                </a>
            </div>
            <!-- div class="collapse navbar-collapse justify-content-end" id="navigation" data-nav-image="resources/img/blurred-image-1.jpg">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="../index.html">Back to Kit</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="https://github.com/creativetimofficial/now-ui-kit/issues">Have an issue?</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" rel="tooltip" title="Follow us on Twitter" data-placement="bottom" href="https://twitter.com/CreativeTim" target="_blank">
                            <i class="fa fa-twitter"></i>
                            <p class="hidden-lg-up">Twitter</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" rel="tooltip" title="Like us on Facebook" data-placement="bottom" href="https://www.facebook.com/CreativeTim" target="_blank">
                            <i class="fa fa-facebook-square"></i>
                            <p class="hidden-lg-up">Facebook</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" rel="tooltip" title="Follow us on Instagram" data-placement="bottom" href="https://www.instagram.com/CreativeTimOfficial" target="_blank">
                            <i class="fa fa-instagram"></i>
                            <p class="hidden-lg-up">Instagram</p>
                        </a>
                    </li>
                </ul>
            </div-->
        </div>
    </nav>
    <!-- End Navbar -->
    <div class="page-header" filter-color="orange">
        <div class="page-header-image" style="background-image:url(resources/img/header.jpg)"></div>
        <div class="container" data-ng-controller="loginController">
            <div class="col-md-8 content-center">
                <div class="card card-login card-plain">
                    <form class="form" novalidate name="myForm">
                        <div class="header header-primary text-center">
                            <!-- div class="logo-container"-->
                            	<h5>Pupil Arena</h5>
                                <!-- img src="resources/img/now-logo.png" alt=""-->
                            <!-- /div-->
                        </div>
                        <div class="content">
                            <div class="input-group" data-ng-class="myForm.firstname.$touched && myForm.firstname.$invalid ? 'has-danger form-group-no-border' : myForm.firstname.$touched ? 'has-success form-group-no-border' : 'form-group-no-border'">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons users_circle-08"></i>
                                </span>
                                <input type="text" class="form-control" name="firstname" placeholder="First Name..." data-ng-model="firstname" required="required" data-ng-class="myForm.firstname.$touched && myForm.firstname.$invalid ? 'form-control-danger' : myForm.firstname.$touched ? 'form-control-success'">
                            </div>
                            
                            <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons ui-1_lock-circle-open"></i>
                                </span>
                                <input type="text" placeholder="Middle Name..." name="middlename" class="form-control" data-ng-model="middlename"/>
                            </div>
                            <div class="input-group" data-ng-class="myForm.lastname.$touched && myForm.lastname.$invalid ? 'has-danger form-group-no-border' : myForm.lastname.$touched ? 'has-success form-group-no-border' : 'form-group-no-border'">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons ui-1_lock-circle-open"></i>
                                </span>
                                <input type="text" placeholder="Last Name..." name="lastname" required="required" class="form-control" data-ng-model="lastname" data-ng-class="myForm.lastname.$touched && myForm.lastname.$invalid ? 'form-control-danger' : myForm.lastname.$touched ? 'form-control-success'"/>
                            </div>
                             <div class="input-group" data-ng-class="myForm.username.$touched && myForm.username.$invalid ? 'has-danger form-group-no-border' : myForm.username.$touched ? 'has-success form-group-no-border' : 'form-group-no-border'">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons users_circle-08"></i>
                                </span>
                                <input type="email" class="form-control" name="username" required="required" placeholder="Email Id..." data-ng-model="username" data-ng-class="myForm.username.$touched && myForm.username.$invalid ? 'form-control-danger' : myForm.username.$touched ? 'form-control-success'">
                            </div>
                            
                            <div class="input-group" data-ng-class="myForm.password.$touched && myForm.password.$invalid ? 'has-danger form-group-no-border' : myForm.password.$touched ? 'has-success form-group-no-border' : 'form-group-no-border'">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons ui-1_lock-circle-open"></i>
                                </span>
                                <input type="password" placeholder="Password..." name="password" required="required" class="form-control" data-ng-model="password" data-ng-minlength="6" data-ng-class="myForm.password.$touched && myForm.password.$invalid ? 'form-control-danger' : myForm.password.$touched ? 'form-control-success'"/>
                            </div>
                             <div class="input-group" data-ng-class="myForm.school.$touched && myForm.school.$invalid ? 'has-danger form-group-no-border' : myForm.school.$touched ? 'has-success form-group-no-border' : 'form-group-no-border'">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons users_circle-08"></i>
                                </span>
                                <input type="text" class="form-control" name="school" required="required" placeholder="School Name..." data-ng-model="school_name" data-ng-class="myForm.school.$touched && myForm.school.$invalid ? 'form-control-danger' : myForm.school.$touched ? 'form-control-success'">
                            </div>
                            
                            <div class="input-group form-group-no-border input-lg">
                                <div class="radio checkbox-primary">
								    <input type="radio" name="sex" data-ng-model="sex" id="radio1" value="Male">
								    <label for="radio1">
								        Male
								    </label>
								</div>
								
								<div class="radio checkbox-primary">
								    <input type="radio" name="sex" data-ng-model="sex" id="radio2" value="Female" checked="checked">
								    <label for="radio2">
								        Female
								    </label>
								</div>
                            </div>
                             <div class="input-group form-group-no-border input-lg">
                             	<div class="checkbox checkbox-primary">
								    <input id="checkbox1" type="checkbox" data-ng-model="isLeader" ng-true-value="'Leader'" ng-false-value="'Member'">
								    <label for="checkbox1">
								        I want to be Team Leader!!
								    </label>
								</div>
                             </div>
                            <!-- Error Message if Any -->
	                        <div data-ng-show="showMessage">
		                        <div class="alert alert-danger" role="alert">
									<div class="container">
										<div class="alert-icon">
											<i class="now-ui-icons objects_support-17"></i>
										</div>
										<strong>Oh snap!</strong> {{message}}
										<!-- button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">
												<i class="now-ui-icons ui-1_simple-remove"></i>
											</span>
										</button -->
									</div>
								</div>
							</div>
                        </div>
                        
                        <div class="footer text-center">
                            <a href="" data-ng-click="signUp()" class="btn btn-primary btn-round btn-lg btn-block">Register</a>
                        </div>
                        <div class="pull-left">
                            <h6>
                                <a href="#" class="link">Login</a>
                            </h6>
                        </div>
                        <div class="pull-right">
                            <h6>
                                <a href="#" class="link">Need Help?</a>
                            </h6>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <footer class="footer">
            <div class="container">
                <nav>
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
                <div class="copyright">
                    &copy;
                    <script>
                        document.write(new Date().getFullYear())
                    </script>, Designed by
                    <a href="#" target="_blank">PR9</a>. Coded by
                    <a href="#" target="_blank">PR9</a>.
                </div>
            </div>
        </footer>
    </div>
</body>
<!--   Core JS Files   -->
<script src="resources/js/core/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="resources/js/core/tether.min.js" type="text/javascript"></script>
<script src="resources/js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="resources/js/plugins/bootstrap-switch.js"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="resources/js/plugins/nouislider.min.js" type="text/javascript"></script>
<!--  Plugin for the DatePicker, full documentation here: https://github.com/uxsolutions/bootstrap-datepicker -->
<script src="resources/js/plugins/bootstrap-datepicker.js" type="text/javascript"></script>
<!-- Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc -->
<script src="resources/js/now-ui-kit.js" type="text/javascript"></script>

</html>