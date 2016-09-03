Information adapted from: http://docs.enginehub.org/manual/worldguard/latest/host-keys/


#**Host Keys**

Frequently in the past, Minecraft had failures in its login code where players could login to a server as any player, including administrators and moderators. Between 2010 and 2013, exploits of this nature were made public five times, frequently leading to thousands of servers being hacked.

The host keys feature of PrimoHostAuth is an extra barrier to impersonation. It works because an extra piece of information, not known by Mojang, has to be sent from the client during login to a server. Even if an attacker were able to break Minecraft’s login system and join as a moderator, because the attacker’s game would lack this piece of information, the server could detect impersonation.

Note: Security breaches of this nature are less common these days.

#How It Works

When a player connects to a server with an address, say play.example.com, Minecraft will tell the server that the player connected with that address. A moderator could connect to a special, secret secretmod.play.example.com address, and the server could easily check whether the address used by the moderator started with secretmod.

The host keys feature allows you to configure an an accepted address for certain players. If a player on the list connects with an incorrect address, he or she is kicked immediately.

#Configuration

Setup is done using the Configuration:

    host-keys:
        97215d3e-ec07-41dd-8482-7556f6f8835e: bagels.play.example.com
        01cb7175-a79a-4128-a02c-bb2c093b51b4: manoverboard.play.example.com
#DNS Configuration

To make this work, you have to make bagels.play.example.com and manoverboard.play.example.com point to your server. However, you should not add specific records for the domains that you use, because this allows attackers to easily figure out the secret domains.

Rather, it is recommended that you setup “wildcard addresses.” An example of a wildcard address may be *.play.example.com, which would mean that any prefix would work (aa.play.example.com, ab.play.example.com, ac.play.example.com, etc.).

Tip: If you don’t have a domain name or can’t set a wildcard address, you can use xip.io.
Alternatives

An alternative to host keys, although not provided by PrimoHostAuth, is to use some sort of login command that takes a password.

