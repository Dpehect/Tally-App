Tally
=====

Tally is a web-based form creation experience designed to make building structured, professional forms feel as natural as editing a document. The project combines a focused product landing page, an interactive block-based form editor, and a transparent pricing experience in a single ASP.NET Core application.

[Live Product](https://tally.so) | [Source Repository](https://github.com/Dpehect/Tally-App)

Project Overview
----------------

The application explores how a traditionally configuration-heavy workflow can be reduced to a direct content-editing experience. Instead of presenting users with complex administration screens, the form builder uses editable content blocks and a compact insertion toolbar.

The current implementation contains three primary product surfaces:

- A responsive landing page that communicates the product value proposition, core capabilities, privacy positioning, and supported use cases.
- A block-based form creation workspace where users can edit the form title and insert multiple field types.
- A pricing page that presents Free and Pro plans through a clear feature comparison.

The project is structured as an ASP.NET Core 8 Razor Pages application. Pages are rendered on the server while lightweight browser-side JavaScript provides the editor interactions. This approach keeps the runtime architecture small, reduces client-side dependencies, and makes the application straightforward to run and deploy.

Core Functionality
------------------

Landing Experience

- Responsive product introduction with a focused call to action.
- Feature presentation for unlimited forms, privacy, input blocks, conditional logic, calculated fields, uploads, signatures, payments, ratings, and scheduling.
- Reusable card, grid, navigation, quote, logo-wall, and call-to-action patterns.
- Adaptive layouts for desktop, tablet, and mobile viewports.
- Semantic page structure and descriptive metadata for discoverability.

Form Builder

- Editable form title using an inline content-editing experience.
- Dynamic block insertion without a full page reload.
- Support for text content, short answers, long answers, multiple-choice questions, email fields, and file-upload fields.
- Editable question labels.
- Visual field previews that clearly distinguish authoring controls from respondent inputs.
- Save and publish actions represented within the editor workflow.
- Compact toolbar designed to keep form construction fast and understandable.

Pricing

- Dedicated Free and Pro plan presentation.
- Responsive two-column comparison that collapses cleanly on smaller screens.
- Clear feature hierarchy and conversion-focused calls to action.
- Visual emphasis for the Pro plan without reducing the visibility of the free offering.

Technology Stack
----------------

| Area | Technology | Responsibility |
| --- | --- | --- |
| Application platform | ASP.NET Core 8 | HTTP pipeline, application hosting, and Razor Pages integration |
| Server-rendered UI | Razor Pages | Page composition, routing, and maintainable server-side templates |
| Frontend | HTML5 and CSS3 | Semantic structure, responsive layouts, visual design, and component styling |
| Interactivity | Vanilla JavaScript | Dynamic form-block creation and editor actions |
| Build system | .NET SDK | Dependency restoration, compilation, execution, and publishing |

Architecture
------------

The application uses a deliberately compact Razor Pages architecture.

```text
Tally-App/
└── Web/
    ├── Pages/
    │   ├── Index.cshtml
    │   ├── Create.cshtml
    │   └── Pricing.cshtml
    ├── Properties/
    ├── Program.cs
    ├── Tally.csproj
    ├── appsettings.json
    └── appsettings.Development.json
```

`Program.cs` configures the ASP.NET Core host, registers Razor Pages, enables static-file handling, and maps page routes.

`Pages/Index.cshtml` contains the main product experience. Its content and styles are organized around reusable visual patterns such as feature cards, responsive grids, calls to action, and product capability groups.

`Pages/Create.cshtml` provides the authoring workspace. JavaScript creates blocks from predefined field templates and appends them to the editor while preserving editable question labels.

`Pages/Pricing.cshtml` implements the pricing comparison with responsive plan cards and direct navigation into the form-creation flow.

Request Lifecycle
-----------------

```text
Browser request
      |
      v
ASP.NET Core middleware pipeline
      |
      v
Razor Pages endpoint routing
      |
      v
Server-rendered HTML response
      |
      v
Browser-side editor interactions
```

The application does not require a client-side framework or a separate frontend build pipeline. This reduces initial setup cost and keeps page delivery predictable. Interactive editor behavior is isolated to the form-creation page and implemented with native browser APIs.

Routes
------

| Route | Purpose |
| --- | --- |
| `/` | Product landing page and feature overview |
| `/create` | Interactive form-building workspace |
| `/pricing` | Free and Pro plan comparison |

Local Development
-----------------

Prerequisites

- .NET 8 SDK
- Git
- A modern browser such as Chrome, Safari, Firefox, or Edge

Clone the repository:

```bash
git clone https://github.com/Dpehect/Tally-App.git
cd Tally-App/Web
```

Restore project dependencies:

```bash
dotnet restore
```

Run the application:

```bash
dotnet run
```

Open the HTTP or HTTPS address printed in the terminal. The exact development port is determined by the local launch configuration.

For automatic restart during development:

```bash
dotnet watch run
```

Production Build
----------------

Create an optimized Release build:

```bash
dotnet build --configuration Release
```

Publish a deployment-ready output:

```bash
dotnet publish --configuration Release --output ./publish
```

The generated `publish` directory contains the application binaries and runtime assets required by an ASP.NET Core-compatible hosting environment.

Deployment
----------

Live product: [https://tally.so](https://tally.so)

The application can be deployed to any platform that supports ASP.NET Core 8, including Azure App Service, AWS Elastic Beanstalk, Google Cloud Run, Railway, Render, Fly.io, a Linux virtual server, or a container-based environment.

An example multi-stage Dockerfile can use the .NET SDK image for compilation and the smaller ASP.NET runtime image for execution:

```dockerfile
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /src
COPY Web/Tally.csproj Web/
RUN dotnet restore Web/Tally.csproj
COPY Web/ Web/
RUN dotnet publish Web/Tally.csproj \
    --configuration Release \
    --output /app/publish \
    --no-restore

FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS runtime
WORKDIR /app
COPY --from=build /app/publish .
ENV ASPNETCORE_URLS=http://+:8080
EXPOSE 8080
ENTRYPOINT ["dotnet", "Tally.dll"]
```

Environment Configuration
-------------------------

ASP.NET Core loads configuration from `appsettings.json`, environment-specific configuration files, command-line arguments, and environment variables.

The runtime environment should be set explicitly in production:

```bash
ASPNETCORE_ENVIRONMENT=Production
```

Container and managed-hosting deployments should also define the listening URL or platform-provided port when required:

```bash
ASPNETCORE_URLS=http://+:8080
```

Design and Engineering Decisions
--------------------------------

Server-rendered pages were selected to keep the delivery model simple and fast. The application does not need a JavaScript framework to present its core content, which avoids a separate Node.js toolchain and reduces the amount of code executed in the browser.

Vanilla JavaScript is used only where interaction is necessary. The form builder creates new input blocks directly in the document, making the authoring workflow responsive without introducing global client-side state management.

Responsive behavior is implemented with CSS Grid, Flexbox, fluid spacing, and targeted breakpoints. Major layout changes are driven by available screen width rather than device-specific assumptions.

The visual system relies on shared design tokens for typography, colors, spacing, borders, and shadows. This provides consistency across the landing, editor, and pricing experiences while keeping the codebase accessible to developers who need to extend it.

Quality Considerations
----------------------

- Semantic headings and page regions improve content structure and assistive-technology navigation.
- Viewport-aware layouts support common desktop, tablet, and mobile dimensions.
- Native controls and familiar interaction patterns reduce the learning curve.
- Server rendering provides useful HTML before client-side JavaScript executes.
- The limited dependency surface reduces maintenance and supply-chain overhead.
- Release publishing separates development concerns from deployment artifacts.

Current Engineering Scope
-------------------------

The repository currently focuses on product presentation and the form-authoring interface. The next production engineering stage would connect the existing interface to persistent domain services.

Recommended additions include:

- Form and submission persistence through Entity Framework Core.
- Authentication and authorization with ASP.NET Core Identity or an external identity provider.
- Form ownership, team workspaces, and role-based permissions.
- Public respondent routes generated from stable form identifiers.
- Server-side validation and anti-abuse controls.
- Conditional-logic evaluation and calculated fields.
- File storage with type, size, and malware validation.
- Submission analytics and export workflows.
- Payment-provider and webhook integrations.
- Automated unit, integration, accessibility, and end-to-end tests.
- Structured logging, health checks, telemetry, and exception monitoring.
- Content Security Policy and production security headers.

Potential Backend Model
-----------------------

A production data model can be organized around the following core entities:

| Entity | Responsibility |
| --- | --- |
| `User` | Account identity and profile data |
| `Workspace` | Team-level ownership and collaboration boundary |
| `Form` | Form metadata, status, settings, and ownership |
| `FormBlock` | Ordered question or content definition |
| `Submission` | One completed or partial respondent session |
| `SubmissionValue` | Typed answer associated with a form block |
| `Integration` | External destination and synchronization configuration |

This structure separates authored form definitions from respondent data and supports versioning, analytics, permissions, and integration processing without coupling those concerns to the Razor page layer.

Testing Strategy
----------------

A complete testing approach should include:

- Unit tests for form rules, validation, plan limits, and conditional logic.
- Integration tests for Razor Page handlers, persistence, authentication, and authorization.
- End-to-end tests for form creation, publication, submission, and pricing navigation.
- Accessibility checks for keyboard navigation, labels, focus order, and color contrast.
- Responsive visual regression tests across representative viewport sizes.
- Performance checks for page rendering, static assets, and high-volume submission endpoints.

Security Considerations
-----------------------

Production form platforms process user-generated and potentially sensitive content. A complete implementation should therefore enforce authorization on every form-management operation, validate all submitted data on the server, protect state-changing requests against cross-site request forgery, rate-limit public submission endpoints, scan uploaded files, and store secrets outside the repository.

Privacy requirements should be reflected in retention policies, deletion workflows, audit logging, encryption, consent handling, and access controls. Operational logging should avoid recording raw form answers unless explicitly required and appropriately protected.

Skills Demonstrated
-------------------

- ASP.NET Core application configuration and Razor Pages routing
- Responsive interface engineering with CSS Grid and Flexbox
- Progressive enhancement with framework-independent JavaScript
- Product-oriented information architecture
- Component-level visual consistency
- Mobile-first layout adaptation
- Release build and deployment planning
- Security, persistence, and scalability analysis

Repository
----------

Source code: [https://github.com/Dpehect/Tally-App](https://github.com/Dpehect/Tally-App)

Live product: [https://tally.so](https://tally.so)
