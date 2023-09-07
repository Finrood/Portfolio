<!-- src/routes/about/+layout.svelte -->

<script>
    import pico from '@picocss/pico';
</script>

<div id="page">
    <nav class="center">
        <a>My Site</a>
    </nav>

    <main class="center">
        <slot />
    </main>

    <footer class="center">© Acme Corporation 2022</footer>
</div>

<style>
    :global(html) {
        overflow-y: scroll;
    }

    #page {
        display: flex;
        flex-direction: column;
        min-height: 100vh;
        gap: 5vw;
    }

    .center {
        padding-left: max(1rem, calc(50vw - 350px));
        padding-right: max(1rem, calc(50vw - 350px));
    }

    nav,
    footer {
        text-transform: uppercase;
        font-size: 0.7rem;
        letter-spacing: 0.1px;
    }

    nav {
        background: rgba(0, 0, 0, 0.4);
        font-weight: 500;
        padding-top: 1rem;
        padding-bottom: 1rem;
    }

    footer {
        text-align: center;
        padding: 3rem 0;
        color: #777;
        margin-top: auto;
    }
</style>