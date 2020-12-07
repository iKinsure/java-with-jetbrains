# Error Correcting Encoder-Decoder

Program codes, sends as binary and decode. 
- Send file: send.txt
- Encode file: encoded.txt
- Receive file: received.txt
- Decode file: decoded.txt


Encode mode
```
Write a mode: 
> encode

send.txt:
text view: Test
hex view: 54 65 73 74
bin view: 01010100 01100101 01110011 01110100

encoded.txt:
expand: ..0.101. ..0.100. ..0.110. ..0.101. ..0.111. ..0.011. ..0.111. ..0.100.
parity: 01001010 10011000 11001100 01001010 00011110 10000110 00011110 10011000
hex view: 4A 98 CC 4A 1E 86 1E 98
```

Send mode
```
Write a mode: 
> send

encoded.txt:
hex view: 4A 98 CC 4A 1E 86 1E 98
bin view: 01001010 10011000 11001100 01001010 00011110 10000110 00011110 10011000

received.txt:
bin view: 00001010 00011000 11001101 01001011 00011100 10010110 00011111 00011000
hex view: 0A 18 CD 4B 1C 96 1F 18
```

Decode mode
```
Write a mode: 
> decode

received.txt:
hex view: 0A 18 CD 4B 1C 96 1F 18
bin view: 00001010 00011000 11001101 01001011 00011100 10010110 00011111 00011000

decoded.txt:
correct: 01001010 10011000 11001100 01001010 00011110 10000110 00011110 10011000
decode: 01010100 01100101 01110011 01110100
hex view: 54 65 73 74
text view: Test
```
