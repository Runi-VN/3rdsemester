# Tuesday Exercise - Fundamental Network Topics
[Exercise link - Fundamental Network Topics](https://docs.google.com/document/d/1RbYJ9hyiqSSASZfuWQtsKwFv7ngQP1-4dceRrKy1LzA/edit)

-   What is your public IP address right now, and how did you find it?  

Using the website http://ip4.me/ I got the following results:  
School: 5.179.80.204  
Home: 87.61.232.155  
    
-   What is your private IP address right now (do this both at home and in school), and who/what gave you that address?  

With the use of the Command Prompt command **ipconfig** I got the following results:  
School: 192.168.56.1 (Ethernet) & 10.50.138.198 (Wi-Fi)   
Home: 192.168.1.83 (Ethernet)

-   What’s special about these address ranges?
    
      -   10.0.0.0 – 10.255.255.255
      -   172.16.0.0 – 172.31.255.255
      -   192.168.0.0 – 192.168.255.255
      
Private range IPs meant for use with private networks and *NOT* online.
![image](https://i.imgur.com/1EJgw9X.png)
    
-   What’s special about this ip-address: 127.0.0.1?

Also known as Localhost (thanks to DNS(?)), this IP is a private IP running on the host.   
Localhost bypasses any network interface hardware (NIC) and is run using the Loopback interface. This means that a request goes through the layer, but once it reaches the NIC it is looped back to the host.  
Essentially it will be run locally without access to the remote network.  
We use localhost for (local) testing purposes before implementing a service on the (remote) web.
    
-   What kind of service would you expect to find on a server using these ports: 22, 23, 25, 53, 80, 443?  

Port 22 is used for the SSH (Secure Shell) Remote Login Protocol. *(For gaining remote access)*  
Port 23 is used for the Telnet protocol. *("Replaced" by SSH)*  
Port 25 is used for SMTP (Simple Mail Transfer Protocol). *(For handling mail)*  
Port 53 is used for the DNS (Domain Name System) Protocol. *(Name/IP resolution service)*  
Port 80 is used for HTTP (Hypertext Transfer Protocol). *(Handles web requests with the user of a browser application)*  
Port 443 is used for the HTTPS (Secure HTTP)/SSL (Secure Socket Layer) Protocol. 

As with any port, the server in question listens to the specific port to provide its service.
    
-   What is the IP address of studypoints.dk and how did you find it?

With the use of the Command Prompt and the command **ping <website\>** I got the following results:  
(Could have used **tracert <website\>** too) 
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

Thanks to the DNS server, which works to figure out the IP address based on the URL address. [Details](https://www.cloudflare.com/learning/dns/what-is-dns/)
    
-   Explain shortly the purpose of an ip-address and a port-number and why we need both

The IP address is for the "location" of your device on a network.   
Often you will have a private network, where each device will have a private IP address, and these devices will connect to a gateway host. ("Router")  
The gateway will then connect to the public network, using it's public IP address.

Port numbers specify the specific service requested from the host. Ports are used to share network resources efficiently and simultaneously.
    
-   What is your (nearest) DNS server,?

I don't know about my nearest, as I manually use Googles public DNS 8.8.8.8/8.8.4.4.  
Can be found using the CMD command **ipconfig /all**.
    
-   What is (conceptually) the DNS system and the purpose with a DNS Server?

DNS is often explained as *the phone book of the internet*. IP addresses are used as computers work with binary numbers, something that may not work as well with humans. The DNS handles my request to `facebook.com` by contacting several services to ensure the actual IP Address `31.13.72.36`.
![Image](https://www.cloudflare.com/img/learning/dns/what-is-dns/dns-lookup-diagram.png)
*[Details of step 1-10](https://www.cloudflare.com/learning/dns/what-is-dns/)*
    
-   What is your current Gateway, and how did you find it?

I don't know about my *current* gateway, but my default is 10.50.128.1 (school, wifi) & 192.168.1.1 (home, ethernet).
    
-   What is the address of your current DHCP-Server, and how did you find it?

Using **ipconfig /all** it is 10.255.1.10 (school, wifi) 192.168.1.1 (home, ethernet). 

Funnily enough both my DHCP and Gateway are the same (probably due to my really, really simple router):
![image](https://i.imgur.com/aSpeb2B.png)
    
-   Explain (conceptually) about the TCP/IP-protocol stack
    
-   Explain about the HTTP Protocol (the following exercises will go much deeper into this protocol)
    
-   Explain (conceptually) how HTTP and TCP/IP are connected (what can HTTP do, and where does it fit into TCP/IP)

# nslookup, ipconfig, ping, netstat, tracert, telnet
