# JSON Database

Program uses java sockets to get a jsons from clients.
Jsons are stored in files on server field.
Program is multithreaded and uses libraries such as google.gson API to serialize jsons and jCommander framework on client field to parse commands.

Accepted client arguments:
- -t - type of server operation - get, set, delete, exit
- -k - key for json storing
- -v - values of json
- -in - read json with properties from file

```
Server started!

pool-1-thread-1 : received: {"type":"set","key":"mykey","value":"my value"}
pool-1-thread-1 : sent: {"response":"OK"}

pool-1-thread-2 : received: {"type":"set","key":"person","value":{"name":"Elon Musk","car":{"model":"Tesla Roadster","year":"2018"},"rocket":{"name":"Falcon 9","launches":"87"}}}
pool-1-thread-2 : sent: {"response":"OK"}

pool-1-thread-3 : received: {"type":"set","key":["person","rocket","launches"],"value":"107 times, with 105 full mission successes"}
pool-1-thread-3 : sent: {"response":"OK"}

pool-1-thread-3 : received: {"type":"get","key":["person","rocket"]}
pool-1-thread-3 : sent: {"response":"OK","value":{"name":"Falcon 9","launches":"107 times, with 105 full mission successes"}}

pool-1-thread-4 : received: {"type":"delete","key":["person","rocket"]}
pool-1-thread-4 : sent: {"response":"OK"}

pool-1-thread-4 : received: {"type":"get","key":"person"}
pool-1-thread-4 : sent: {"response":"OK","value":{"name":"Elon Musk","car":{"model":"Tesla Roadster","year":"2018"}}}

pool-1-thread-4 : received: {"type":"delete","key":"person"}
pool-1-thread-4 : sent: {"response":"OK"}

pool-1-thread-4 : received: {"type":"get","key":"person"}
pool-1-thread-4 : sent: {"response":"ERROR","reason":"No such key"}

pool-1-thread-4 : received: {"type":"exit"}
pool-1-thread-4 : sent: {"response":"OK"}

Process finished with exit code 0
```
