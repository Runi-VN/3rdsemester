# Fundamental Network Topics
[Exercise link](https://docs.google.com/document/d/1RbYJ9hyiqSSASZfuWQtsKwFv7ngQP1-4dceRrKy1LzA/edit)

-   What is your public IP address right now, and how did you find it?  

Using the website http://ip4.me/ I got the following results:  
School: 5.179.80.204  
Home:  
    
-   What is your private IP address right now (do this both at home and in school), and who/what gave you that address?  

With the use of the Command Prompt and the command **ipconfig** I got the following results:  
School: 192.168.56.1 (Ethernet) & 10.50.138.198 (Wi-Fi) 
Home: 

-   What’s special about these address ranges?
    
      -   10.0.0.0 – 10.255.255.255
      -   172.16.0.0 – 172.31.255.255
      -   192.168.0.0 – 192.168.255.255
      
Private range IPs (**elaborate**)
    
-   What’s special about this ip-address: 127.0.0.1?

Also known as Localhost (thanks to DNS(?)), this IP is a private IP running on the host. Localhost bypasses any network interface hardware and is run using the Loopback interface. Essentially it can be run locally without access to the remote network.  
We use localhost for (local) testing purposes before implementing a service on the (remote) web.
    
-   What kind of service would you expect to find on a server using these ports: 22, 23, 25, 53, 80, 443?  

Port 22 is used for the SSH (Secure Shell) Remote Login Protocol.  
Port 23 is used for the Telnet protocol. 
Port 25 is used for the SMTP (Simlple Mail Transfer Protocol) Protocol.  
Port 53 is used for the DNS (Domain Name System) Protocol.  
Port 80 is used for the HTTP (Hypertext Transfer Protocol) Protocol.  
Port 443 is used for the HTTPS/SSL (Secure Socket Layer).  
    
-   What is the IP address of studypoints.dk and how did you find it?

With the use of the Command Prompt and the command **ping <website\>** I got the following results:  
`Studypoints.dk`:  
```C:\Users\runin>ping studypoints.dk

Pinging studypoints.dk [165.227.137.75] with 32 bytes of data:
Request timed out.
Request timed out.
Request timed out.
Request timed out.

Ping statistics for 165.227.137.75:
Packets: Sent = 4, Received = 0, Lost = 4 (100% loss)
```  
`Studypoints.info`:   
```C:\Users\runin>ping studypoints.info

Pinging studypoints.info [157.230.21.145] with 32 bytes of data:
Reply from 157.230.21.145: bytes=32 time=18ms TTL=52
Reply from 157.230.21.145: bytes=32 time=23ms TTL=52
Reply from 157.230.21.145: bytes=32 time=17ms TTL=52
Reply from 157.230.21.145: bytes=32 time=23ms TTL=52

Ping statistics for 157.230.21.145:
    Packets: Sent = 4, Received = 4, Lost = 0 (0% loss),
Approximate round trip times in milli-seconds:
    Minimum = 17ms, Maximum = 23ms, Average = 20ms
 ```

-   If you write https://studypoints.dk in your browser, how did “it” figure out that it should go to the IP address you discovered above?

Thanks to the DNS server. **elaborate**
    
-   Explain shortly the purpose of an ip-address and a port-number and why we need both
    
-   What is your (nearest) DNS server,?

I don't know about my nearest, as I manually use Googles 8.8.8.8/8.8.4.4.  
Can be found using the CMD command **ipconfig /all**.
    
-   What is (conceptually) the DNS system and the purpose with a DNS Server?
    
-   What is your current Gateway, and how did you find it?

I don't know about my *current* gateway, but my default is 10.50.128.1 (school, wifi) & xx.xx.xxx.x (home, ethernet). **elaborate**
    
-   What is the address of your current DHCP-Server, and how did you find it?

Using **ipconfig /all** it is 10.255.1.10 (school, wifi) and xx.xx.xx.xx (home, ethernet). **elaborate**
    
-   Explain (conceptually) about the TCP/IP-protocol stack
    
-   Explain about the HTTP Protocol (the following exercises will go much deeper into this protocol)
    
-   Explain (conceptually) how HTTP and TCP/IP are connected (what can HTTP do, and where does it fit into TCP/IP)

# nslookup, ipconfig, ping, netstat, tracert, telnet
