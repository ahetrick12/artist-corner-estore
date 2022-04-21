---
geometry: margin=1in
---
# PROJECT Design Documentation

## Team Information
* Team name: TEAM 8
* Team members
  * Alex Hetrick
  * Alex Martinez
  * Kara Kolodinsky
  * Jonathan Campbell
  * Daniel Kench

## Executive Summary

### Purpose
Our program is an e-store targeted towards fans of a tattoo artist who sells their designs on products. 
Customers are able to buy those products, learn information via the announcements feature, and chat in a chatroom with other users. 

### Glossary and Acronyms

| Term | Definition |
|------|------------|
| SPA | Single Page |
| DAO | Data access object |


## Requirements

This section describes the features of the application.

- Inventory management
- Item browsing
- Authentication (login/logout)
- Cart management
- Checking out
- Viewing and posting announcements
- Data persistence
- Chatting with other users

### Definition of MVP
A store where you can view items for sale by the artist, add these items to your cart, and then purchase them. You can also login to an account in order to save your cart, view store-wide announcements that the artist has posted, and chat with other users. As the owner of the store, the artist, you can add, delete, or update items in the inventory, as well as post store-wide announcements to the site.

### MVP Features
- Inventory management
- Item browsing
- Authentication (login/logout)
- Cart management
- Checking out
- Data persistence

### Roadmap of Enhancements
> _Provide a list of top-level features in the order you plan to consider them._


## Application Domain

This section describes the application domain.

![Domain Model](domain-model.png)

The customer can browse an inventory of products on the website, and add the ones they like to the shopping cart. If logged in, this shopping cart will persist between sessions. The user can then checkout and purchase the items in the cart. If the owner is logged in, they are able to update the inventory and also post store-wide annoucenements, which the customers can see. The customers also have the option to contact the owner to request custom paintings, and the customers will be able to communicate about that though an in-site messaging service.

## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

The e-store web application, is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistance. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The ViewModel provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the ViewModel and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts
with the e-store application.

> _Provide a summary of the application's user interface.  Describe, from
> the user's perspective, the flow of the pages in the web application._


### View Tier

Our View Tier UI includes a login page where a user can create an account or login into an account that has already been made. The View Tier also includes a
homepage and products page (where the user can view products for sale). There is also a cart page (where the user can view items in their cart) and subsequently
a checkout page (where the user can purchase the items in their cart) and a confirmation page that confirms their purchase. Finally, there are message and
announcement pages (where the user can view announcements made by the admin and speak to other users). There is also an about page and contanct page (where the 
user can learn more about the admin)
![image](https://user-images.githubusercontent.com/98925856/164358138-7407ac85-5604-47e6-855a-d025cc3fbe66.png)
[Team_8_Sequence_diagram.pdf](https://github.com/RIT-SWEN-261-04/team-project-2215-swen-261-04-8/files/8526787/Team_8_Sequence_diagram.pdf)


### ViewModel Tier
> _Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

> _At appropriate places as part of this narrative provide one or more
> static models (UML class diagrams) with some details such as critical attributes and methods._


### Model Tier
The model tier contains components that are repesentations of objects such as store items, announcements, and messages. Each object has properties that can be set and retrieved individually.
![Model UML](model.png)
The ViewModel tier translates file information into these objects that are then sent to the view tier to be sent to the front end.

### Static Code Analysis/Design Improvements
> _Discuss design improvements that you would make if the project were
> to continue. These improvement should be based on your direct
> analysis of where there are problems in the code base which could be
> addressed with design changes, and describe those suggested design
> improvements._

> _With the results from the Static Code Analysis exercise, 
> discuss the resulting issues/metrics measurements along with your analysis
> and recommendations for further improvements. Where relevant, include 
> screenshots from the tool and/or corresponding source code that was flagged._

## Testing
> _This section will provide information about the testing performed
> and the results of the testing._

### Acceptance Testing
> _Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

### Unit Testing and Code Coverage
Our unit testing strategy was to try to efficiently cover as much as possible.
> _Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets. If there are any anomalies, discuss
> those._
