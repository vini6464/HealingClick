
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Admin</title>

    <!-- Bootstrap -->
   <link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
   
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

	<script>
	$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})
</script>
	
	
	<style>
	@import url(https://fonts.googleapis.com/css?family=Josefin+Sans:600);
	#edit{border-bottom:1px solid white;color:black;}
	body{font-family: 'Josefin Sans', sans-serif;
	background:url(image/.jpg);
	background-size:100% 800px;
    background-repeat: no-repeat;}
	
	




	</style>
	
  </head>
  <body>
  
  <div style="min-height:800px;background: -webkit-linear-gradient(left,  rgba(14,175,165, 0.8), rgba(255,255,255, 0.8)); /* For Safari 5.1 to 6.0 */
    background: -o-linear-gradient(right,  rgba(14,175,165, 0.8), rgba(255,255,255, 0.8)); /* For Opera 11.1 to 12.0 */
    background: -moz-linear-gradient(right,  rgba(14,175,165, 0.8), rgba(255,255,255, 0.8)); /* For Firefox 3.6 to 15 */
    background: linear-gradient(to right,  rgba(14,175,165, 0.8), rgba(255,255,255, 0.8)); /* Standard syntax (must be last) */
} "> 
	
			<nav class="navbar navbar-default" role="navigation" style="background:white;border:none;">
				<div class="navbar-header">
					 
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
					</button> <a class="navbar-brand" href="index.php" style="background-color:;letter-spacing:1px;font-weight:bold;"> LOGO </a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right" style="font-size:1.2em;font-weight:bold;background-color:rgba(234,82,65, 0.8);">
						
						
						
					</ul>
					
					
					
				</div>
				
			</nav>
	
   

   

		
		
		
	
	



<br>
<br>
<br>
<br>
<br>
<br>


 <div class="container">
 <div class="row">
 <div class="col-sm-4 col-md-4 column"> </div>
 <div class="col-sm-4 col-md-4 column"> 
 <form name="" method="post" action="login.admin">
 <div class="message">${error}</div>
 
        <input type="text" name="username" class="form-control" placeholder="Admin id" required>
		<input type="password" name="password" class="form-control" placeholder="password" required>
		<input type="submit" name="submit" value="Submit"  class=" form-control btn" style="color:;letter-spacing:1px;font-weight:bold;margin-top:2px;border:none;">
     </form>
	
	</div>
	<div class="col-sm-4 col-md-4 column"> </div>
</div>
</div>
</div>


 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>