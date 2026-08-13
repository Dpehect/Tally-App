# Tally.so Clone (ASP.NET Core 8)

This is a simplified landing-page + demo form-builder clone of [Tally.so](https://tally.so), built with ASP.NET Core 8 Razor Pages.

## Features included in this demo

- Landing page styled after the official Tally homepage
- Simple interactive form builder (`/create`) with block insertion
- Pricing page (`/pricing`)
- Responsive design, Inter-like typography, magenta accent color

## How to run

```bash
cd TallyClone
dotnet restore
dotnet run
```

Then open https://localhost:5xxx (or the port shown in the console).

## Notes

- This is **not** a full functional clone of Tally.
- Real Tally has a full Notion-style editor, conditional logic engine, payments via Stripe, e-signatures, team workspaces, integrations (Zapier, Notion, Slack…), analytics, custom domains, etc.
- Images are loaded from the official tally.so CDN for demo purposes.
- Fair-use / branding: this project is for educational / personal reference only.

## Project structure

```
TallyClone/
├── Pages/
│   ├── Index.cshtml      # Landing page
│   ├── Create.cshtml     # Demo form builder
│   └── Pricing.cshtml    # Pricing page
├── Program.cs
├── TallyClone.csproj
└── README.md
```

Built with .NET 8.
