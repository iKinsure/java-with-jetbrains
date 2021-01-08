<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Code</title>
    <link rel="icon" type="image/png" sizes="16x16" href="/favicon/favicon.png">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/styles/dracula.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
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
    <br>
    <span id="load_date">${date}</span>
    <#if "${views}" != "0">
        <span id="views_restriction">views: ${views}</span>
    </#if>
    <#if "${time}" != "0">
        <span id="time_restriction">time: ${time}</span>
    </#if>
    <pre><code>${code}</code></pre>
    <br>

</body>
</html>
