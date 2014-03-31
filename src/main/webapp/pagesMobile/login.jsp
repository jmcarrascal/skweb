
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<body>
<!-- HOME -->
<div data-role="page" id="home" data-title="Home">
<div data-role="header" data-position="inline" data-theme="a"><a
	href="http://www.facultia.com/blog" data-icon="arrow-u" data-theme="b"
	rel="external">Blog</a>
<h1>Grupos</h1>
<a href="#login" data-icon="check" data-theme="b">Login</a></div>
<!-- /header -->
<div data-role="content">
<ul data-role="listview" data-filter="true" data-theme="a"
	data-dividertheme="b" data-inset="true">
	<li><a href="#nav1">ARRANQUE BD 35X45  SIN FUELLE CRISTAL X 2 KG.<span class="ui-li-count">Exi1 <b> 23</b></span></a></li>
	<li><a href="#nav1">George V <span class="ui-li-count">Reinó 25 años</span></a></li>
	<li><a href="#nav1">Prince of Wales <span class="ui-li-count">N/A</span></a></li>
	<li><a href="#nav1">Elizabeth I <span class="ui-li-count">Reinó	44 años</span></a></li>
	<li><a href="#nav1">Elizabeth II<span class="ui-li-count">Reina	desde 1952</span></a></li>
</ul>
</div>
<!-- /content -->

<div data-role="footer" data-position="fixed">
<div data-role="navbar">
<ul>
	<li><a href="#" class="ui-btn-active" data-icon="refresh"
		data-theme="b">Actualizar</a></li>
	<li><a href="#" data-icon="search" data-theme="b">Buscar</a></li>
	<li><a href="#" data-icon="gear" data-theme="b">Ajustes</a></li>
</ul>
</div>
<!-- /navbar --></div>
<!-- /footer --></div>
<!-- /page home -->

<!-- LOGIN -->
<div data-role="page" id="login" data-title="Login">
<div data-role="header" data-theme="b">
<h1>Login</h1>
<a href="#home" data-icon="home" data-iconpos="notext"
	class="ui-btn-right"></a></div>
<!-- /header -->

<div data-role="content" data-theme="a">
<form action="#" method="post" id="login">
<div data-role="fieldcontain"><label for="username">Usuario</label>
<input type="text" name="_username" id="username"
	placeholder="Nombre de usuario" autofocus required /></div>

<div data-role="fieldcontain"><label for="password">Password</label>
<input type="password" name="_password" id="password" value=""
	placeholder="Introduzca password" required /></div>

<div class="ui-grid-a">
<div class="ui-block-a">
<button type="submit" data-theme="e">Login</button>
</div>
<div class="ui-block-b"><a data-role="button" href="#home">Cancel</a>
</div>
</div>
</form>
</div>
<!-- /content -->

<div data-role="footer" data-theme="b">
<h4>&copy; Facultia</h4>
</div>
<!-- /footer --></div>
<!-- /page login -->

</body>