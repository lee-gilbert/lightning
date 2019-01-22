

# UI Stack:
* Angular v7.2.1, angular reactive forms + angular material.
* RXJS 6.3.3
* RESTful backend API.

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


## Running the Angular UI App

1a)  App can be run from command line (Note: node v11 must be installed first.):

cmd\> `cd ui` 
<br> Run cmd\> `npm install` to update packages.
<br> Run cmd\> `ng serve` to start a dev server. Navigate to `http://localhost:4200/`

The UI requires the backend also to be started.
Follow instructons in `.\ltk\readme.md` 

## Known Issues
- If an attempt is made to save a Proposal with a topic that already exists on the backend (i.e. is duplicated), no validation error is displayed, and this also prevents the proposal being saved until the topic name is changed.

## Optional UI todos

- Add Security & user account registry
- Add table column sort & topic search
- Refactor directory structure of UI to follow a 'best practice' standard.
- Expand UI test suites
- Race condition detection.
- Multilingual support
* Security has not been implemented at this time. There are several options e.g.
  * Implement via Nginx, with Nginx acting as a reverse proxy and setting/forwarding required tokens on http requests.  The angular App would need to forward these on rest calls to the backend.
  * Implement via Angular, here the angular App would still need to forward these on rest calls to the backend.
  * Use an SSO scheme, managed e.g. via a seperate keycloak server, with signon in either Nginx or Angular.
