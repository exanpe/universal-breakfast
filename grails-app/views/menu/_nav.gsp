<!-- Navbar -->
<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a href="${createLinkTo(dir:'')}" class="navbar-brand">Universal Breakfast</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${createLinkTo(dir:'')}">Home</a></li>
                <sec:ifNotLoggedIn>
                    <li><a href="#contact">Contact</a></li>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li><a href="#"><ub:teamName /></a></li>
                    <li><a href="${createLink(controller: 'logout')}"><i class="fa fa-power-off"></i></a></li>
                </sec:ifLoggedIn>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container -->
</nav>
