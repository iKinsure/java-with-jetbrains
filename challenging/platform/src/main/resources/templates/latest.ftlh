<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Latest</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
    <#list snippets as snippet>
        <span id="load_date">${snippet.date}</span>
        <pre id="code_snippet"><code>${snippet.code}</code></pre>
    </#list>
</body>
</html>
