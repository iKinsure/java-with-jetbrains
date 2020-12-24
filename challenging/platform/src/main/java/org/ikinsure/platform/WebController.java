package org.ikinsure.platform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class WebController {

    @GetMapping(path = "/code")
    public String get(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "        <head>\n" +
                "                <meta charset=\"utf-8\">\n" +
                "                <title>Code</title>\n" +
                "                <style type=\"text/css\">\n" +
                "                        #code_snippet {\n" +
                "\n" +
                "                        }\n" +
                "                        #load_date {\n" +
                "      \n" +
                "                        }\n" +
                "                        body {\n" +
                "                                color: white;\n" +
                "                                background-color: black\n" +
                "                        }\n" +
                "                </style>\n" +
                "        </head>\n" +
                "        <body>\n" +
                "                <pre id=\"code_snippet\">"+CodeSharingPlatform.code.getCode()+"</pre>\n" +
                "                <span id=\"load_date\">"+CodeSharingPlatform.code.getDate()+"</span>\n" +
                "        </body>\n" +
                "</html>";
    }

    @GetMapping(path = "/code/new")
    public String submit(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Create</title>\n" +
                "    <style type=\"text/css\">\n" +
                "        #code_snippet {\n" +
                "            width: auto;\n" +
                "            height: auto;\n" +
                "            min-height: 20%;\n" +
                "            min-width: 40%;\n" +
                "            margin: 5% 5% 5% 5%;\n" +
                "        }\n" +
                "        #send_snippet {\n" +
                "\n" +
                "        }\n" +
                "        body {\n" +
                "            align-items: center;\n" +
                "            color: white;\n" +
                "            background-color: black\n" +
                "        }\n" +
                "    </style>\n" +
                "    <script>\n" +
                "        function send() {\n" +
                "        let object = {\n" +
                "        \"code\": document.getElementById(\"code_snippet\").value\n" +
                "        };\n" +
                "\n" +
                "        let json = JSON.stringify(object);\n" +
                "\n" +
                "        let xhr = new XMLHttpRequest();\n" +
                "        xhr.open(\"POST\", '/api/code/new', false)\n" +
                "        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "        xhr.send(json);\n" +
                "\n" +
                "        if (xhr.status == 200) {\n" +
                "        alert(\"Success!\");\n" +
                "        }\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "    <body>\n" +
                "        <textarea id=\"code_snippet\">\n" +
                "        </textarea>\n" +
                "        <button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>\n" +
                "    </body>\n" +
                "</html>\n";
    }
}
