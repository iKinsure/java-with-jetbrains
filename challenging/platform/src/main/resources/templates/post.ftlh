<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Create</title>
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
                <textarea id="code_snippet" class="field-style" placeholder="Tutaj umieść kod"></textarea>
            </li>
            <li>
                <input type="number" id="views_restriction" class="field-style field-split align-left" placeholder="Liczba wyświetleń" />
                <input type="number" id="time_restriction" class="field-style field-split align-right" placeholder="Czas życia w sekundach" />
            </li>
            <li>
                <input id="send_snippet" type="submit" value="Wyślij" onclick="send()"/>
            </li>
        </ul>
    </form>
</body>
</html>
