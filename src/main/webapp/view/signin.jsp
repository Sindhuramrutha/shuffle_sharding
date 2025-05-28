<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:400,600,700"/>
  <link rel="stylesheet" type="text/css" href="donator_signin.css">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
</head>
<body style="background-image: url('index.jpg');  background-attachment: fixed; 
background-size: 100%;">
  <div class="body-div">
    <div id="cont" class="container">
      <div id="login_div" class="main-div">
        <h3 style="align-self: flex-start; padding: 10px 0px;"> User Login</h3>
        
        <form action="/donator_signin_login" method="POST">
          <input type="text" placeholder="Email" id="email_field" name="email" required/>
          <input type="password" placeholder="Password" id="password_field" name="password" required/>
          <button type="submit" id="btn_login">login</button><br>
          <p style="padding: 10px;">Don't have an account? <a href="/donator_signup" style="color: #4285f4;"> Register </a></p>
        </form>
    
      </div>
      <div>
        <img height="300px" width="300px" src="https://www.enter-world.org/images/donations.jpg" alt="">
      </div>
    </div>
  </div>
</body>
<script>
</script>
</html>