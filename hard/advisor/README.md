# Music Advisor

App connects with user Spotify accounts using OAuth 2.0.
Available options:
- auth
- new
- featured
- categories
- playlists \*id\*
- prev
- next
- exit

Options can be passed through args.
Arguments: -page \*entries per single page\*

```
> new
Please, provide access for application.

> auth
use this link to request the access code:
https://accounts.spotify.com/authorize?client_id=6e70a3870fbe4d4bae4982467b032a2f&redirect_uri=http://localhost:8080/&response_type=code
waiting for code...
code received
making http request for access_token... 
Success!

> categories
Toplijsten
2020 Wrapped
Thuis
Pop
Stemming
---PAGE 1 OF 4---

> prev
No more pages.

> next
Decennia
Hip Hop
In de auto
Gaming
Wellness
---PAGE 2 OF 4---

> playlists gaming
Top Gaming Tracks
https://open.spotify.com/playlist/37i9dQZF1DWTyiBJ6yEqeu

Nerding Around
https://open.spotify.com/playlist/37i9dQZF1DX3YGn6btf5lC

Power Gaming
https://open.spotify.com/playlist/37i9dQZF1DX6taq20FeuKj

Video Game Soundtracks
https://open.spotify.com/playlist/37i9dQZF1DXdfOcg1fm0VG

League of Legends Official Playlist
https://open.spotify.com/playlist/37i9dQZF1DWVi45nh2EuPP

---PAGE 1 OF 3---

> playlists in de auto
Specified id doesn't exist

> plelist gaming
Invalid option. Try again.

> exit
---GOODBYE!---
```
