

# LightningTalks Demo:

##### UI Stack
[![Angular version](https://img.shields.io/badge/Angular-12.2.5-red.svg?style=flat-square)](https://angular.io/)
[![AngularMaterial version](https://img.shields.io/badge/Material-12.2.5-red.svg?style=flat-square)](https://angular.io/)
[![Typescript version](https://img.shields.io/badge/Typescript-4.3.5-blue.svg?style=flat-square)](https://angular.io/)
[![Rxjs version](https://img.shields.io/badge/Rxjs-6.6.7-ff69b4.svg?style=flat-square)](https://angular.io/)
[![jasmine](https://img.shields.io/badge/jasmine-3.6.0-orange.svg?style=flat-square)](https://angular.io/)
         
##### Backend
[![Java](https://img.shields.io/badge/Java-v8-blue.svg?style=flat-square)](https://spring.io/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.0.8-red.svg?style=flat-square)](https://spring.io/)
[![Spring](https://img.shields.io/badge/Spring-5-red.svg?style=flat-square)](https://spring.io/)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-5-red.svg?style=flat-square)](https://spring.io/)
[![lombok](https://img.shields.io/badge/Lombok-1.16-purple.svg?style=flat-square)](https://www.postgresql.org/)
[![JPA](https://img.shields.io/badge/JPA-2.1-orange.svg?style=flat-square)](https://spring.io/)
[![Hibernate](https://img.shields.io/badge/Hibernate-5.2-orange.svg?style=flat-square)](https://spring.io/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-10.2-blue.svg?style=flat-square)](https://www.postgresql.org/)
[![Liquibase](https://img.shields.io/badge/Liquibase-3.5.5-blue.svg?style=flat-square)](https://www.postgresql.org/)
[![log4j2](https://img.shields.io/badge/Log4j2-2.10-blue.svg?style=flat-square)](https://www.postgresql.org/)
[![junit](https://img.shields.io/badge/Junit-4-green.svg?style=flat-square)](https://www.postgresql.org/)

##### DevOps & Analytics
[![Docker](https://img.shields.io/badge/Docker-17.05-blue.svg?style=flat-square)](https://www.docker.com/)
[![ElasticSearch](https://img.shields.io/badge/ElasticSearch-6.6.0-yellow.svg?style=flat-square)](https://www.elastic.co/products/elasticsearch)
[![Logstash](https://img.shields.io/badge/Logstash-6.6.0-blue.svg?style=flat-square)](https://www.elastic.co/products/logstash)
[![Kibana](https://img.shields.io/badge/Kibana-6.6.0-blueviolet.svg?style=flat-square)](https://www.elastic.co/products/kibana)


##### Build Tooling
[![Maven](https://img.shields.io/badge/Mvn-3.x-yellow.svg?style=flat-square)](https://www.elastic.co/products/elasticsearch)
[![Npm](https://img.shields.io/badge/Npm-6.6-pink.svg?style=flat-square)](https://angular.io/)


**Lighning Talks App ** is composed of three layers:

 - **Client**: UI made using **[Angular](https://angular.io/)**

 - **Server**: Service Api exposed over http REST made using **[Spring Boot](https://spring.io/)**

 - **Database**: **[PostgreSQL](https://www.postgresql.org/)**

<p align="justify">
The App runs inside <a href="https://www.docker.com/"><b>Docker</b></a> containers, linked together with <a href="https://docs.docker.com/compose/overview/"><b>Docker Compose</b></a>. As Analytics framework, the <a href="https://www.elastic.co/products"><b>Elastic Stack</b></a> has been used by sending data of interest to Elastic Search, through Logstash, and querying and analyzing it with Kibana.
</p>

## Setup With Docker

### Linux:
1. Install [docker](https://docs.docker.com/install/), [docker-compose](https://docs.docker.com/compose/install/) and [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

2. Clone this repo:<pre>git clone https://github.com/lee-gilbert/lightning.git </pre> <pre>cd lightning/</pre>

3. For Java Backend, Run:<pre>docker-compose -p lightning up -d </pre>

# Dev-Mode: Running the Angular UI App

1a)  App can be run from command line (Note: node v11 must be installed first.):

cmd\> `cd ui` 
<br> Run cmd\> `npm install` to update packages.
<br> Run cmd\> `ng serve` to start a dev server. Navigate to `http://localhost:4200/`

The UI requires the backend also to be started.
Optionally: Follow instructons in `.\ltk\readme.md` to run in dev without docker locally.

## What this App Demo Does
Supports creation of Talk TopicProposals via a Angular Material UI. These can be saved/edited then submitted for Approval.
Up to 3 talks can be approved for the next scheduled session which by default occurs on the 1st tuesday of even months.
Approved Talk Submissions are then associated with a ScheduledSession object.

## Design considerations for use

* When a new Proposal is created, workflow ensures it must be saved first before it can be submitted for peer review (where it may be selected for a Talk session.)
This is intended to avoid hasty submission during edit, and allows the user to revise it before submitting, without having to leave the editor 'open'.

* A Submitted proposal automatically acquires the next available valid presentation date, without the user having to enter it.
(A date selector could be provided if required.)

* When a Proposal is submitted for talk consideration, the submission is tracked by the serverside-backend as a seperate object.
The original Proposal object also remains available for view/edit, until it is deleted.
(Retaining the original proposal is an enabler to allow possible incremental new feature support e.g. a revised proposal re-submit for a different presentation date. )

* Field input data Validation is implemented both on the UI and backend.  

* Development caveats
  * Build env profiles for dev & prod have not yet been implemented.
  * The Table page size of 5 rows was set to make testing easier during dev.
  * The backend currently inserts some data for testing.


## Todos

- Add Security: OAuth2 SSO, JWT & user account registry, and a 'talk approver' group.
- Add ui view table support for filtering by session date (currently the date selector has no filter logic behind it.)
- Add ui view table column sort & topic search
- Make use of Springs Result Paging API (for large results).
- Migrate to Spring Reactive Flux/Mono Based Restful services.
- Expand UI test suites to include services mock.
- DB Race condition detection.
- Support Kubernaties Cluster Deployment.
- Migrate to Serverless Stack.
    - Use Secrets Store e.g. Hashicorp Vault.
    - Use Spring Session persistance e.g. Redis.
- Multilingual support.
