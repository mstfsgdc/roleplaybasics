# Role Play Basics

Role Play Basics is a light-weight, configurable basic role play chat commands plugin. You can easily configure the plugin settings via **config.yml** file and easily reload the plugin without any restart process with **/roleplaybasics reload** command. With seperated permissions, you can easily manage the users that who'll be able to use each command.

# Permissions
-   roleplaybasics.admin - Allows the usage of the /roleplaybasics command. (Default: op)
-   roleplaybasics.me - Allows the usage of the /me command. (Default: true)
-   roleplaybasics.do - Allows the usage of the /do command. (Default: true)
-   roleplaybasics.local - Allows the usage of the /local command. (Default: true)
-   roleplaybasics.shout - Allows the usage of the /shout command. (Default: true)
-   roleplaybasics.whisper - Allows the usage of the /whisper command. (Default: true)

# Others
-   Message distances:

Local: same as chat-distance in config.yml  
Shout: 1.5 times more than chat-distance in config.yml  
Whisper: 6 times less than chat-distance in config.yml  
Me: same as chat-distance in config.yml  
Do: same as chat-distance in config.yml

Also message colors are specified for distances. For example:  
local-message-color is for 15 blocks (half of chat-distance),  
local-distance-message-color is for far players more than 15 blocks.

# Contributor(s)
- #### [FurKqn](https://www.mc-tr.com/uyeler/furkqn.49531/) (Idea: looc & ooc)