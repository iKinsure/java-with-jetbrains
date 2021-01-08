<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Find code by id</title>
    <link rel="icon" type="image/png" sizes="16x16" href="/favicon/favicon.png">
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/send.js"></script>
</head>
<body>
<nav id="topnav">
    <ul class="menu">
        <li><a href="/">Strona główna</a></li>
        <li><a href="/code/latest">Ostatnie</a></li>
        <li><a href="/code/new">Nowy kod</a></li>
        <li><a href="/code/">Znajdź</a></li>
    </ul>
</nav>
<form class="former">
    <ul>
        <li>
            <textarea id="code_snippet" class="field-style" placeholder="Tutaj umieść id kodu"></textarea>
        </li>
        <li>
            <input id="send_snippet" type="submit" value="Wyślij" onclick="window.open('/code/' + document.getElementById('code_snippet').value)"/>
        </li>
    </ul>
</form>
</body>
</html>
