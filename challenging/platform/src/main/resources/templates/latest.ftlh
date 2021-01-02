<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Latest</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/styles/dracula.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <#list snippets as snippet>
        <br>
        <span id="load_date">${snippet.date}</span>
        <pre><code>${snippet.code}</code></pre>
        <br>
    </#list>
</body>
</html>
