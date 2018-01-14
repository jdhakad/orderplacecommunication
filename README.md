# Order place communication
System Will be used to send communication once order is placed by user.

Data flow

Once order is coming to this system we will push it in order.event queue
Listener will listen the order data and will get params info for same and template context
Once we have params info and template context we will send it to communication.template queue
Listener will take template context and will decide all the channel by which communication will go and will push it to
communication.router queue.

Communication router will listen and will send the communication via all the channels mentioned in the ctx

Due to some reason if communication failed then we have retry strategy also, we can track also like what is success/failure
rate.

 TODO

 Create queue -
 queue name - order.placed,             exchange-name - order.event
 queue name - order.pickedup,           exchange-name - order.event
 queue name - communication.router,     exchange-name - communication.router
 queue name - communication.template,   exchange-name - communication.template
