<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                    </button>
                    <g:link action="index" class="navbar-brand">Universal Breakfast</g:link>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active">
                            <g:link action="index">Home</g:link>
                        </li>
                        <li><a href="#contact">Contact</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container -->
        </nav>

        <div class="carousel">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="fill"></div>
                    <div class="carousel-caption">
                        <h2>Your Collaborative Breakfast</h2>
                        <a href="#" class="button">LOGIN</a>
                        <a href="#" class="button">REGISTER</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="section-colored home" id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-md-9 col-sm-8">
                        <h2>One question, one bug or simply get In touch ?</h2>
                    </div>
                    <div class="col-md-3 col-sm-4">
                        <a href="#" class="btn btn-danger btn-lg">Contact us</a>
                    </div>
                </div>
            </div>
        </div>

	</body>
</html>
